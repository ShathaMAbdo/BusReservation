package se.BTH.BusReservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.BTH.BusReservation.domain.Tour;
import se.BTH.BusReservation.domain.Trip;
import se.BTH.BusReservation.repositories.TourRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TourService {
    @Autowired
    private TourRepository tourRepo;

    //addTour method for insert tour into tour table
    public void addTour(Tour tour) {
        tourRepo.save(tour);
    }

    //uppdateTour method for modify tour into tour table
    public void uppdateTour(Tour tour) {
        tourRepo.save(tour);
    }

    //deletTour method for delete tour from tour table
    public void deletTour(Tour tour) {
        tourRepo.delete(tour);
    }

    //getTourById method for get tour from tour table by id
    public Optional<Tour> getTourById(Integer id) {
        return tourRepo.findById(id);
    }

    //getAllTours method for get all tours from tour table
    public List<Tour> getAllTours() {
        return tourRepo.findAll();
    }

    //searchTour method for find tour from tour table by first and last location
    public List<Tour> searchTour(String fl, String ll) {
        return tourRepo.findByFirstLocationAndLastLocation(fl, ll);
    }

    //searchtripsTour method for find tour from tour table by trips locations
    public List<Trip> searchtripsTour(String dl, String al) {
        return tourRepo.findByDLocationORALocation(dl, al);
    }

    //isValidateOptions method return true if trips are sequential by time
    public boolean isValidateOptions(List<Trip> Otrip) {
        for (int j = 0; j < Otrip.size() - 1; j++) {// not same tour and not validate for time departure
            if ((!(Otrip.get(j).getTour().getTourId().equals(Otrip.get(j + 1).getTour().getTourId()))) &&
                    (Otrip.get(j).getDTime().isAfter(Otrip.get(j + 1).getDTime())))
                return false;
        }
        return true;
    }

    //getConection method for return location for change tour
    public String getConection(List<Trip> trips) {
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getTour().getTourId() != trips.get(i + 1).getTour().getTourId())
                return trips.get(i).getaLocation();
        }
        return "";
    }

    //isConection method return true if there is connection location in list of trips
    public boolean isConection(List<Trip> trips) {
        for (int i = 0; i < trips.size(); i++) {
            if (i + 1 < trips.size())
                if (trips.get(i).getTour().getTourId() != trips.get(i + 1).getTour().getTourId())
                    return true;
        }
        return false;
    }

    //getATimeConection method for get arrival time for connection location in trips
    public LocalTime getATimeConection(List<Trip> trips) {
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getTour().getTourId() != trips.get(i + 1).getTour().getTourId())
                return trips.get(i).calcArrivalTime();
        }
        return null;
    }

    //getDTimeConection method for get departure time for connection location in trips
    public LocalTime getDTimeConection(List<Trip> trips) {
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getTour().getTourId() != trips.get(i + 1).getTour().getTourId())
                return trips.get(i + 1).getDTime();
        }
        return null;
    }

    //isFreeSeat method return true if there is free seat in trips
    public boolean isFreeSeat(List<Trip> Otrip) {
        for (int j = 0; j < Otrip.size(); j++) {//there is free seat for all trips
            if (Otrip.get(j).getNumSeats() <= 0)
                return false;
        }
        return true;
    }

    //getSeat method gets free seat number in trips
    public int getSeat(List<Trip> Otrip) {
        Integer seat = -0;
        for (int j = 0; j < Otrip.size(); j++) {//decrease number of seat
            seat = Otrip.get(j).getNumSeats();
            Otrip.get(j).setNumSeats(Otrip.get(j).getNumSeats() - 1);
        }
        return seat;
    }

    //totalPrice method calc prices for all trips list
    public int totalPrice(List<Trip> Otrip) {
        int total = 0;
        for (int j = 0; j < Otrip.size(); j++) {//decrease number of seat
            total += Otrip.get(j).getPrice();
        }
        return total;
    }
}
