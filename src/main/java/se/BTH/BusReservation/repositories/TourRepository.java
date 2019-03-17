package se.BTH.BusReservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.BTH.BusReservation.domain.Tour;
import se.BTH.BusReservation.domain.Trip;

import java.util.List;

@Repository //TourRepository is interface extends either CrudRepository or JpaRepository
public interface TourRepository extends JpaRepository<Tour, Integer> {

    // Special abstract method for find tour by first and last locations that used HQL
    @Query("SELECT t FROM Tour t where t.firstLocation = ?1 AND t.lastLocation = ?2")
    List<Tour> findByFirstLocationAndLastLocation(String firstLocation, String lastLocation);

    // Special abstract method for find tour by Special departure and arrival trips locations that used HQL
    @Query(value = "SELECT t.trips FROM Tour t inner join where trip p.dLocation = ?1 OR p.aLocation = ?2  group by trips", nativeQuery = true)
   List<Trip> findByDLocationORALocation(String dLocation, String aLocation);


}
