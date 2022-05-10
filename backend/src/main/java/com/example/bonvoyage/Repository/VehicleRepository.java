package com.example.bonvoyage.Repository;

import com.example.bonvoyage.Models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    @Query("select v from Vehicle v where v.location.Id = :id  ")
    List<Vehicle> findAllByLocationId(@Param("id") Integer id);
}
