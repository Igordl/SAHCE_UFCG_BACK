package com.ufcg.sahce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.sahce.domain.Place;
import com.ufcg.sahce.domain.user.MyUser;
import com.ufcg.sahce.service.MyUserService;
import com.ufcg.sahce.service.PlaceService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private MyUserService myUserService;

    @GetMapping("/place/{id}")
    public Place getPlace(@PathVariable Long id) {
        return placeService.findById(id);
    }

    @GetMapping("/admin/place")
    public List<Place> getAllPlaces() {
        return placeService.findAll();
    }

    @GetMapping("/places/user/{userId}")
    public List<Place> getAllPlacesByUserId(@PathVariable Long userId) {
        MyUser user = myUserService.findById(userId);
        return placeService.findAllByUserId(user);
    }

    @PostMapping("/place")
    public Place createPlace(@RequestBody Place place) {
        return placeService.create(place);
    }

    @DeleteMapping("/place/{id}")
    public void deletePlace(@PathVariable Long id) {
        placeService.deleteById(id);
    }

}
