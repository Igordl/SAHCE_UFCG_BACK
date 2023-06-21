package com.ufcg.sahce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ufcg.sahce.domain.Schedule;
import com.ufcg.sahce.domain.Scheduling;
import com.ufcg.sahce.service.SchedulingService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @GetMapping("/scheduling/{placeId}")
    public List<Schedule> getSchedulesByPlace(@PathVariable Long placeId) {
        return schedulingService.findByPlaceId(placeId);

    }

    @PostMapping("/scheduling")
    public Scheduling createScheduling(@RequestBody Scheduling scheduling) {
        return schedulingService.create(scheduling);

    }

    @GetMapping("/scheduling/{userId}")
    public List<Scheduling> getSchedulesByUser(@PathVariable Long userId) {
        return schedulingService.findByUserId(userId);

    }

    @GetMapping("/admin/scheduling")
    public List<Scheduling> getAllSchedules() {
        return schedulingService.findAll();

    }

}
