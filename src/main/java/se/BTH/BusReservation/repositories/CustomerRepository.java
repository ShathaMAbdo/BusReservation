package se.BTH.BusReservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.BTH.BusReservation.domain.Customer;

import java.util.List;

@Repository //CustomerRepository is interface extends either CrudRepository or JpaRepository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

     // Special abstract method for find customer by national id
     List<Customer> findByNational(Integer national);

     // Special abstract method for find customer by name
     List<Customer> findByName(String name);

     // Special abstract method for find customer by email
     List<Customer> findByEmail(String email);

}
