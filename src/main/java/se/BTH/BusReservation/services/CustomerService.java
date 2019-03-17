package se.BTH.BusReservation.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import se.BTH.BusReservation.domain.Customer;
import se.BTH.BusReservation.repositories.CustomerRepository;


import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository custRepo;

    //addCustomer method for insert customer into customer table
    public void addCustomer(Customer customer) {
        custRepo.save(customer);
    }

    //uppdateCustomer method for modify customer into customer table
    public void uppdateCustomer(Customer customer) {
        custRepo.save(customer);
    }

    //deletCustomer method for delete customer from customer table
    public void deletCustomer(Customer customer) {
        custRepo.delete(customer);
    }

    //getCustomerById method for get customer into customer table customer table
    public Optional<Customer> getCustomerById(Integer id) {
        return  custRepo.findById(id);
    }

    //getCustomerByNational method for get customer by national id from customer table
    public List<Customer> getCustomerByNational(Integer national) {
        return  custRepo.findByNational(national);
    }

    //getCustomerByName method for get customer by name from customer table
    public List<Customer> getCustomerByName(String name) {
        return custRepo.findByName(name);
    }

    //getCustomerByEmail method for get customer by email from customer table
    public List<Customer> getCustomerByEmail(String email) {
        return custRepo.findByEmail(email);
    }

    //getCustomerByEmail method for get all customer from customer table
    public List<Customer> getAllCustomers() {
        return custRepo.findAll();
    }

}