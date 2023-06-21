package com.ufcg.sahce.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.sahce.domain.Place;
import com.ufcg.sahce.domain.Schedule;
import com.ufcg.sahce.domain.Scheduling;
import com.ufcg.sahce.domain.user.Guest;
import com.ufcg.sahce.domain.user.MyUser;
import com.ufcg.sahce.repository.GuestRepository;
import com.ufcg.sahce.repository.PlaceRepository;
import com.ufcg.sahce.repository.ScheduleRepository;
import com.ufcg.sahce.service.PlaceService;
import com.ufcg.sahce.service.SchedulingService;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SchedulingService schedulingService;

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public Place create(Place place) {
        List<Schedule> schedules = scheduleRepository.saveAll(place.getSchedules());
        place.setSchedules(schedules);
        return placeRepository.save(place);
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAllActives();
    }

    @Override
    public Place findById(Long id) {
        return placeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Não existe espaço cadastrado com esse id."));
    }

    @Override
    public Place update(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public void deleteById(Long id) {
        Place place = findById(id);
        placeRepository.delete(place);
    }

    @Override
    public List<Place> findAllByUserId(MyUser user) {

        // Busca reservas do usuário
        List<Scheduling> schedules = schedulingService.findByUserId(user.getId());

        // Busca reservas em queele é convidado
        List<Guest> times = guestRepository.findByGuestEmail(user.getEmail());

        // Adiciona os idsDasreservas em que ele é convidado a lista de exclusão de
        // visualização
        for (Guest guest : times) {
            schedules.add(schedulingService.findById(guest.getId()));
        }

        // Transforma a lista de reservas em uma lista de Ids de locais onde tem reserva
        List<Long> idPlaceScheduling = new ArrayList<Long>();
        for (Scheduling sc : schedules) {
            idPlaceScheduling.add(sc.getIdPlace());
        }

        // Busca todos os espaços disponiveis
        List<Place> allPlaces = placeRepository.findAllActives();

        // Remove da lista onde o usuário já tem acesso
        for (Place place : allPlaces) {
            if (place.getRestrict().equals("S") && user.getUserType().equals("EXTERNAL_USER")) {
                System.out.println("Removendo por ser restrito ao usuario: " + place.toString());
                allPlaces.remove(place);
            } else {
                if (contains(idPlaceScheduling, place.getId())) {
                    System.out.println("Removendo por já ter reserva no local: " + place.toString());
                    allPlaces.remove(place);
                }
            }

        }

        return allPlaces;
    }

    private Boolean contains(List<Long> idPlaceScheduling, Long id) {
        for (Long el : idPlaceScheduling) {
            if (el.equals(id)) {
                return true;
            }

        }
        return false;

    }

}
