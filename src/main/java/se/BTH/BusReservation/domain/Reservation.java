package se.BTH.BusReservation.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity //entity reservation creates table with same name
public class Reservation {
    //data fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer reservId;//primary key
    private Integer ticketNum;

    @ManyToOne(targetEntity = Customer.class , fetch = FetchType.LAZY)
    private Customer customer; // the relationship is one customer can have many reservations

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Trip> trips; // the relationship is many trips can have many reservations

    private Integer seatNum;
    private LocalDateTime reservTime;

    //constructors
    public Reservation( ) {
        this.reservTime = LocalDateTime.now();
    }

    public Reservation(Customer customer, List<Trip> trips, Integer seatNum) {
        this();
        this.ticketNum = StaticData.getTicketNum();
        this.customer = customer;
        this.trips = trips;
        this.seatNum = seatNum;
    }

    //getter and setter methods for fields

    public Integer getReservId() {
        return reservId;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public LocalDateTime getReservTime() {
        return reservTime;
    }


    @Override//override tostring method
    public String toString() {
        return "Reservation{" +
                "reservId=" + reservId +
                ", ticketNum=" + ticketNum +
                ", customer=" + customer +
                ", seatNum=" + seatNum +
                ", reservDate=" + reservTime.toLocalDate() + // get just date from dateTime
                ", reservTime=" + reservTime.toLocalTime() +"\n"+// get just time from dateTime
                ", trips=" + trips +
                '}';
    }
}
