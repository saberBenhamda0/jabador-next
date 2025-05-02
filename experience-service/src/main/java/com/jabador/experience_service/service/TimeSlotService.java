package com.jabador.experience_service.service;

import com.jabador.experience_service.entity.Experience;
import com.jabador.experience_service.entity.TimeSlot;
import com.jabador.experience_service.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> save(Experience experienceId){

        List<TimeSlot> timeSlots = new ArrayList<>();

        TimeSlot timeSlot = TimeSlot.builder()
                .endTime("17:05")
                .startTime("17:00")
                .availableSpots(10)
                .date("12/12/2004")
                .currency("MAD")
                .discountedPrice(100)
                .dayOfWeek("MONDAY")
                .formatedDate("12 12 2004")
                .isSpecialOffer(false)
                .originalPrice(120)
                .experience(experienceId)
                .build();

        timeSlots.add(timeSlot);

        timeSlotRepository.save(timeSlot);
        return timeSlots;
    }
}
