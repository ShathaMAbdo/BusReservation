package se.BTH.BusReservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.BTH.BusReservation.domain.Reservation;


@Repository //ReservationRepository is interface extends either CrudRepository or JpaRepository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


}
