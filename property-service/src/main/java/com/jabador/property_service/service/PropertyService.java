package com.jabador.property_service.service;

import com.jabador.property_service.dto.*;
import com.jabador.property_service.entity.*;
import com.jabador.property_service.exception.PropertyDoesExistNotException;
import com.jabador.property_service.repository.AmenitieRepository;
import com.jabador.property_service.repository.MediaRepository;
import com.jabador.property_service.repository.PropertyRepository;
import com.jabador.property_service.repository.UserCacheRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class PropertyService {

    private final String UPLOAD_DIR_FOR_PROPERTY = "uploads/property";

    private final PropertyRepository propertyRepository;
    private final AmenitieRepository amenitieRepository;
    private final MediaRepository mediaRepository;
    private final UserCacheRepository userCacheRepository;

    public PropertyService(PropertyRepository propertyRepository, AmenitieRepository amenitieRepository, MediaRepository mediaRepository, UserCacheRepository userCacheRepository) {
        this.propertyRepository = propertyRepository;
        this.amenitieRepository = amenitieRepository;
        this.mediaRepository = mediaRepository;
        this.userCacheRepository = userCacheRepository;
    }

    public List<ListAllPropertyDTO> getAll(){
        List<Property> properties = propertyRepository.findAll();

        List<ListAllPropertyDTO> propertiesDTOS = new ArrayList<>();
        for(Property property : properties){
            Location location = property.getLocation();
            LocationDTO locationDTO = new LocationDTO(location.getCountry(), location.getCity(), location.getStreet(), location.getPostalCode(), location.getLatitude(), location.getLongitude());
            ListAllPropertyDTO propertyDTO = ListAllPropertyDTO.builder()
                    .id(property.getId())
                    .name(property.getTitle())
                    .type(property.getType())
                    .price(property.getPricing().getBasePrice())
                    .location(locationDTO)
                    .images(property.getMedia())
                    .build();

            propertiesDTOS.add(propertyDTO);
        }
        return propertiesDTOS;
    }

    public Map<String, String> add(AddPropertyDTO addPropertyDTO){

        List<Long> ids = new ArrayList<>();
        for(long amenitie : addPropertyDTO.amenities()){
            ids.add(amenitie);
        }

        List<Amenitie> amenities = amenitieRepository.findAllById(ids);

        List<MediaDTO> mediaDTOS = addPropertyDTO.images();
        List<Media> medias = new ArrayList<>();

        for(MediaDTO mediaDTO : mediaDTOS){

            Path path = saveImage(mediaDTO.image(), mediaDTO.caption());

            Media media = Media.builder()
                    .isPrimary(mediaDTO.isPrimary())
                    .imagesOrder(mediaDTO.order())
                    .url(path.toString())
                    .caption(mediaDTO.caption())
                    .type(mediaDTO.type())
                    .build();

            medias.add(media);
        }

        Location location = Location.builder()
                .country(addPropertyDTO.country())
                .city(addPropertyDTO.city())
                .street(addPropertyDTO.street())
                .postalCode(addPropertyDTO.postalCode())
                .longitude(addPropertyDTO.longitude())
                .latitude(addPropertyDTO.latitude())
                .build();

        Pricing pricing = Pricing.builder()
                .basePrice(addPropertyDTO.price())
                .currency("MAD")
                .taxes(20.00)
                .build();

        Optional<UserCache>  userCache = userCacheRepository.findById(addPropertyDTO.owner());

        Property property = Property.builder()
                .title(addPropertyDTO.propertyName())
                .description(addPropertyDTO.propertyDescription())
                .type(addPropertyDTO.propertyType())
                .owner(userCache.get())
                .rooms(addPropertyDTO.rooms())
                .bedRooms(addPropertyDTO.bedrooms())
                .kitchens(addPropertyDTO.kitchens())
                .bathrooms(addPropertyDTO.bathrooms())
                .maxGuests(addPropertyDTO.maxGuests())
                .amenities(amenities)
                .media(medias)
                .location(location)
                .pricing(pricing)
                .build();

        mediaRepository.saveAll(medias);
        propertyRepository.save(property);

        Map<String, String> response = new HashMap<>();
        response.put("response", "the property has been added with success");
        return response;
    }

    public Property get(long id){
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        Property property = optionalProperty.orElseThrow(() -> new PropertyDoesExistNotException(id));
        return property;
    }

    public Path saveImage(MultipartFile image,String propertyName){

        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR_FOR_PROPERTY));

            String imageType = image.getContentType().split("/")[1];
            String imageFullName = propertyName + UUID.randomUUID() + "." + imageType;

            Path path = Paths.get(UPLOAD_DIR_FOR_PROPERTY, imageFullName);

            Files.write(path, image.getBytes());

            return path;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
