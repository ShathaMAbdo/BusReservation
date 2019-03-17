package se.BTH.BusReservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.BTH.BusReservation.domain.Trip;
import se.BTH.BusReservation.repositories.TripRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    //Data field
    @Autowired
    private TripRepository tripRepo;

    //addTrip method for insert Trip into Trip table
    public void addTrip(Trip trip) {
        tripRepo.save(trip);
    }

    //addTrips method for insert list of Trip into Trip table
    public void addTrips(List<Trip> trips) {
        tripRepo.saveAll(trips);
    }

    //uppdateTrip method for modify Trip from Trip table
    public void uppdateTrip(Trip trip) {
        tripRepo.save(trip);
    }

    //deletTrip method for delete Trip from Trip table
    public void deletTrip(Trip trip) {
        tripRepo.delete(trip);
    }

    //getTripById method for get Trip from Trip table by id
    public Optional<Trip> getTripById(Integer id) {
        return  tripRepo.findById(id);
    }

    //getTripByDLocation method for find Trip from Trip table departure location
    public List<Trip> getTripByDLocation(String dL) {
        return  tripRepo.findByDLocation(dL);
    }

    //getTripByALocation method for find Trip from Trip table by arrival location
    public List<Trip> getTripByALocation(String aL) {
        return  tripRepo.findByALocation(aL);
    }

    //serchTrip method for find Trip from Trip table by departure and arrival location
    public List<Trip> serchTrip(String dLocation,String aLocation){
        return tripRepo.findByDLocationAndALocation(dLocation,aLocation);
    }

    //getTrips method for get all Trip from Trip table
    public List<Trip> getTrips() {
        return  tripRepo.findAll();
    }
}
