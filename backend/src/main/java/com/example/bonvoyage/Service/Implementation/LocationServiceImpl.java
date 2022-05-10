package com.example.bonvoyage.Service.Implementation;

import com.example.bonvoyage.Models.Location;
import com.example.bonvoyage.Repository.LocationRepository;
import com.example.bonvoyage.Service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }
}
