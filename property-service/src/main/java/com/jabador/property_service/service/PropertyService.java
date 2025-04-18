package com.jabador.property_service.service;

import com.jabador.property_service.entity.*;
import com.jabador.property_service.repository.AmenitieRepository;
import com.jabador.property_service.repository.MediaRepository;
import com.jabador.property_service.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final AmenitieRepository amenitieRepository;
    private final MediaRepository mediaRepository;

    public PropertyService(PropertyRepository propertyRepository, AmenitieRepository amenitieRepository, MediaRepository mediaRepository) {
        this.propertyRepository = propertyRepository;
        this.amenitieRepository = amenitieRepository;
        this.mediaRepository = mediaRepository;
    }

    public List<Property> getAll(){
        return propertyRepository.getAllBy();
    }

    public void add(){


        UserCache userCache = UserCache.builder()
                .firstName("test firstname")
                .seconcName("test secondname")
                .gender("test gender")
                .email("email test")
                .profilePicutreUrl("profile pic url test")
                .build();

        List<Amenitie> amenities = List.of(
                Amenitie.builder()
                        .icon("Test icon 1")
                        .name("test name 1")
                        .type("test type 1")
                        .build(),
                Amenitie.builder()
                        .icon("Test icon 2")
                        .name("test name 2")
                        .type("test type 2")
                        .build()
        );

        amenitieRepository.saveAll(amenities);


        List<Media> medias =  List.of(
                Media.builder()
                        .url("test name 1")
                        .type("test type 1")
                        .caption("caption test 1")
                        .isPrimary(true)
                        .imagesOrder(1)
                        .build(),
                Media.builder()
                        .url("test name 2")
                        .type("test type 2")
                        .caption("caption test 2")
                        .isPrimary(false)
                        .imagesOrder(2)
                        .build()
        );

            mediaRepository.saveAll(medias);

        Location location = Location.builder()
                .continent("africa")
                .country("morocco")
                .city("chaouen")
                .street("lobar")
                .postalCode(91000)
                .longitude(5.55)
                .latitude(10.65)
                .build();

        Pricing pricing = Pricing.builder()
                .basePrice(10.00)
                .serviceFee(50.00)
                .cleaningFee(14.00)
                .currency("MAD")
                .taxes(5.00)
                .build();

        Property property = Property.builder()
                .title("test")
                .description("ds test")
                .type("type test")
                .owner(userCache)
                .rooms(0)
                .bedRooms(0)
                .kitchens(0)
                .bathrooms(0)
                .maxGuests(0)
                .amenities(amenities)
                .media(medias)
                .location(location)
                .pricing(pricing)
                .build();

        propertyRepository.save(property);
    }
}
