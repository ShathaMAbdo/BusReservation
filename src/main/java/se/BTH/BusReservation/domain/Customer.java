package se.BTH.BusReservation.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity  // entity customer will create table with same name
public class Customer {
    //Data field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer customerId; // primary Key
    private String name;
    private Integer national;
    private String email;

    //constructors
    public Customer() {
    }

    public Customer(String name, Integer national, String email) {
        this.name = name;
        this.national = national;
        this.email = email;
    }

  // getter and setter methods for fields
    public Integer getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNational() {
        return national;
    }

    public void setNational(Integer  national) {
        this.national = national;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override //override toString method
    public String toString() {
        return "Customer" +
                "  Id=" + customerId +
                "  name='" + name + '\'' +
                "  national number=" + national +
                "  email='" + email + '\'' ;
    }
}
