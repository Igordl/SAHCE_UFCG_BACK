package com.ufcg.sahce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufcg.sahce.domain.Place;
import com.ufcg.sahce.domain.user.MyUser;

@Service
public interface PlaceService {
    Place create(Place place);

    List<Place> findAll();

    Place findById(Long id);

    Place update(Place user);

    void deleteById(Long id);

    public List<Place> findAllByUserId(MyUser user);
}
