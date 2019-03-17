package se.BTH.BusReservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.BTH.BusReservation.domain.Trip;

import java.util.List;

@Repository //TripRepository is interface extends either CrudRepository or JpaRepository
public interface TripRepository  extends JpaRepository<Trip, Integer> {

    // Special abstract method for find find trips by arrival location
    List<Trip> findByALocation(String aLocation);

    // Special abstract method for find trips by departure location
    List<Trip> findByDLocation(String dLocation);

    // Special abstract method for find trips by arrival location and departure location that used HQL
    @Query("SELECT t FROM Trip t where t.dLocation = ?1 AND t.aLocation = ?2")
     List<Trip> findByDLocationAndALocation(String dLocation, String aLocation);


}
