package se.BTH.BusReservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.BTH.BusReservation.domain.Reservation;
import se.BTH.BusReservation.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repo;

    //addReservation method for insert Reservation into Reservation table
    public void addReservation(Reservation res) {
        repo.save(res);
    }

    //addReservation method for modify Reservation into Reservation table
    public void uppdateReservation(Reservation res) {
        repo.save(res);
    }

    //deletReservation method for delet Reservation from Reservation table
    public void deletReservation(Reservation res) {
        repo.delete(res);
    }

    //getReservationById method for get Reservation from Reservation table
    public Optional<Reservation> getReservationById(Integer id) {
        return  repo.findById(id);
    }

    //getAllReservation method for get all Reservations from Reservation table
    public List<Reservation> getAllReservation() {
        return  repo.findAll();
    }



}
