package com.jabador.property_service.service;

import com.jabador.property_service.dto.AddPropertyDTO;
import com.jabador.property_service.dto.LocationDTO;
import com.jabador.property_service.dto.MediaDTO;
import com.jabador.property_service.dto.PricingDTO;
import com.jabador.property_service.entity.*;
import com.jabador.property_service.repository.AmenitieRepository;
import com.jabador.property_service.repository.MediaRepository;
import com.jabador.property_service.repository.PropertyRepository;
import com.jabador.property_service.repository.UserCacheRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
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

    public List<Property> getAll(){
        return propertyRepository.getAllBy();
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

            Media media = Media.builder()
                    .isPrimary(mediaDTO.isPrimary())
                    .imagesOrder(mediaDTO.imagesOrder())
                    .build();

            Path path = saveImage(mediaDTO.image(), addPropertyDTO.propertyName());
            media.setUrl(path.toString());
            media.setType(path.toString());
            media.setCaption(path.toString());
        }

        LocationDTO locationDTO = addPropertyDTO.locationDTO();
        Location location = Location.builder()
                .continent(locationDTO.continent())
                .country(locationDTO.country())
                .city(locationDTO.city())
                .street(locationDTO.street())
                .postalCode(locationDTO.postalCode())
                .longitude(locationDTO.longitude())
                .latitude(locationDTO.latitude())
                .build();

        PricingDTO pricingDTO = addPropertyDTO.pricingDTO();
        Pricing pricing = Pricing.builder()
                .basePrice(pricingDTO.basePrice())
                .serviceFee(pricingDTO.serviceFee())
                .cleaningFee(pricingDTO.cleaningFee())
                .currency(pricingDTO.currency())
                .taxes(pricingDTO.taxes())
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

        propertyRepository.save(property);
        mediaRepository.saveAll(medias);

        Map<String, String> response = new HashMap<>();
        response.put("response", "the property has been added with success");
        return response;
    }

    public Path saveImage(MultipartFile image,String propertyName){

        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR_FOR_PROPERTY));

            String imageType = image.getContentType().split("/")[1];
            String imageFullName = propertyName + "." + imageType;

            Path path = Paths.get(UPLOAD_DIR_FOR_PROPERTY, imageFullName);

            Files.write(path, image.getBytes());

            return path;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
