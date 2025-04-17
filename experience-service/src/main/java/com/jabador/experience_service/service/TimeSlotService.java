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
                .timeslot(5.45)
                .experienceId(experienceId)
                .build();

        timeSlots.add(timeSlot);

        timeSlotRepository.save(timeSlot);
        return timeSlots;
    }
}
