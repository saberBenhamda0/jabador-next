package com.jabador.experience_service.service;

import com.jabador.experience_service.embeddable.CoordinatePoint;
import com.jabador.experience_service.embeddable.Details;
import com.jabador.experience_service.embeddable.Guest;
import com.jabador.experience_service.embeddable.Location;
import com.jabador.experience_service.entity.*;
import com.jabador.experience_service.repository.ExperienceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ImageService imageService;
    private final TimeSlotService timeSlotService;


    public ExperienceService(ExperienceRepository experienceRepository, ImageService imageService, TimeSlotService timeSlotService) {
        this.experienceRepository = experienceRepository;
        this.imageService = imageService;
        this.timeSlotService = timeSlotService;
    }

    public List<Experience> findAll(){
        return experienceRepository.findAll();
    }

    @Transactional
    public void save(){
        CoordinatePoint meetingPoint = CoordinatePoint.builder()
                .x(0.00)
                .y(0.00)
                .build();

        CoordinatePoint experiencePoint = CoordinatePoint.builder()
                .x(10.00)
                .y(10.00)
                .build();

        Location location = Location.builder()
                .codePostal(91000)
                .experienceLocation(experiencePoint)
                .meetingPoint(meetingPoint)
                .continent("test continent")
                .country("test country")
                .city("city test")
                .street("street test")
                .build();

        Guest guest = Guest.builder()
                .adults(0)
                .kids(0)
                .infants(0)
                .build();

        Details details = Details.builder()
                .prepTime(0)
                .duration(10)
                .startDate("2020/12/12")
                .endDate("2020/12/18")
                .minGroupSize(1)
                .maxGroupSize(2)
                .privateGroup(false)
                .primaryLanguague("English")
                .isWheelchairAccesible(true)
                .build();


        Experience experience = Experience.builder()
                .title("title test")
                .description("descritption test")
                .details(details)
                .location(location)
                .guest(guest)
                .pricing(100)
                .build();

        System.out.println("HERE THE DEBUG ");
        System.out.println(experience);
        System.out.println(experience.getId());


        experience.setMainImages(imageService.save(experience));

        experience.setTimeSlot(timeSlotService.save(experience));

        experienceRepository.save(experience);

    }
}
