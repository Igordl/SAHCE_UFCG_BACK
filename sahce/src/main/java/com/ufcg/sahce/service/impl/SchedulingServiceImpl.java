package com.ufcg.sahce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.sahce.domain.Schedule;
import com.ufcg.sahce.domain.Scheduling;
import com.ufcg.sahce.domain.user.Guest;
import com.ufcg.sahce.repository.GuestRepository;
import com.ufcg.sahce.repository.SchedulingRepository;
import com.ufcg.sahce.service.SchedulingService;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public Scheduling create(Scheduling scheduling) {
        List<Guest> guests = guestRepository.saveAll(scheduling.getGuests());
        System.out.println(guests.toString());
        scheduling.setGuests(guests);
        return schedulingRepository.save(scheduling);
    }

    @Override
    public List<Scheduling> findAll() {
        return schedulingRepository.findAll();
    }

    @Override
    public Scheduling findById(Long id) {
        return schedulingRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Não existe esta reserva."));

    }

    @Override
    public Scheduling update(Scheduling scheduling) {
        return schedulingRepository.save(scheduling);
    }

    @Override
    public void deleteById(Long id) {
        schedulingRepository.deleteById(id);
    }

    @Override
    public List<Scheduling> findByUserId(Long id) {
        return schedulingRepository.findByUserId(id);
    }

    @Override
    public List<Schedule> findByPlaceId(Long placeId) {
        return schedulingRepository.findAllByPlaceId(placeId);
    }
}
