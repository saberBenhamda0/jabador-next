package com.jabador.experience_service.service;

import com.jabador.experience_service.embeddable.*;
import com.jabador.experience_service.entity.*;
import com.jabador.experience_service.exception.RessourceNotFoundException;
import com.jabador.experience_service.repository.ExperienceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final MediaService mediaService;
    private final TimeSlotService timeSlotService;


    public ExperienceService(ExperienceRepository experienceRepository, MediaService mediaService, TimeSlotService timeSlotService) {
        this.experienceRepository = experienceRepository;
        this.mediaService = mediaService;
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
                .postalCode(91000)
                .experienceLocation(experiencePoint)
                .meetingPoint(meetingPoint)
                .state("test state")
                .country("test country")
                .city("city test")
                .street("street test")
                .build();

        Guest guest = Guest.builder()
                .adults(0)
                .kids(0)
                .infants(0)
                .build();

        Duration duration = Duration.builder()
                .time(20)
                .flexible(true)
                .build();

        GroupSize groupSize = GroupSize.builder()
                .max(10)
                .min(2)
                .privateGroup(false)
                .privateGroupMaxSize(5)
                .privateGroupMinSize(2)
                .build();

        Details details = Details.builder()
                .prepTime(0)
                .duration(duration)
                .startDate("2020/12/12")
                .endDate("2020/12/18")
                .groupSize(groupSize)
                .primaryLanguague("English")
                .isWheelchairAccesible(true)
                .skillLevel("Beginner")
                .build();


        Pricing price = Pricing.builder()
                .basePrice(100)
                .currency("MAD")
                .privateGroupRate(200)
                .build();

        Experience experience = Experience.builder()
                .title("title test")
                .description("descritption test")
                .details(details)
                .location(location)
                .guest(guest)
                .pricing(price)
                .build();

        System.out.println("HERE THE DEBUG ");
        System.out.println(experience);
        System.out.println(experience.getId());


        experience.setMedia(mediaService.save(experience));

        experience.setTimeSlot(timeSlotService.save(experience));

        experienceRepository.save(experience);

    }

    public Experience getPerId(long id){
        Optional<Experience> optionalExperience = experienceRepository.findById(id);
        Experience experience = optionalExperience.orElseThrow(() -> new RessourceNotFoundException(id));
        return experience;
    }
}
