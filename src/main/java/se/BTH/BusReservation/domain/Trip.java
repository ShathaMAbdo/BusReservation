package se.BTH.BusReservation.domain;

import javax.persistence.*;
import java.time.LocalTime;

@Entity // entity trip will create table with same name
//@Table(name = "trip")
public class Trip {
    //data fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripId;//  primary key
    private String dLocation;
    private String aLocation;
    private LocalTime DTime;
    private Integer price;
    private Integer numSeats;

    @ManyToOne(targetEntity = Tour.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tourId")
    private Tour tour;// the relationship is every tour has many trips

    //constructors
    public Trip(String dLocation, String aLocation, LocalTime DTime, Integer price) {
        this.dLocation = dLocation;
        this.aLocation = aLocation;
        this.DTime = DTime;
        this.price = price;
        this.numSeats = 50;
    }

    public Trip() {
    }

    //getter and setter for fields
    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Integer getTripId() {
        return tripId;
    }

    public String getdLocation() {
        return dLocation;
    }

    public void setdLocation(String dLocation) {
        this.dLocation = dLocation;
    }

    public String getaLocation() {
        return aLocation;
    }

    public void setaLocation(String aLocation) {
        this.aLocation = aLocation;
    }

    public LocalTime getDTime() {
        return DTime;
    }

    public void setDTime(LocalTime DTime) {
        this.DTime = DTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(Integer numSeats) {
        this.numSeats = numSeats;
    }

    // calcTimeD method calc deparure time for next trips in tour
    public static LocalTime calcTimeD(String dL, String aL, LocalTime StartTime) {
        if (StaticData.TravelTime[Location.valueOf(dL).getIndex()][Location.valueOf(aL).getIndex()] != 0)
            return StartTime.plusMinutes(StaticData.delay + StaticData.TravelTime[Location.valueOf(dL).getIndex()][Location.valueOf(aL).getIndex()]);
        else
            return StartTime.plusMinutes(StaticData.delay + StaticData.TravelTime[Location.valueOf(aL).getIndex()][Location.valueOf(dL).getIndex()]);

    }

    //calcArrivalTime method calc arrival time for trips
    public LocalTime calcArrivalTime() {
        if (StaticData.TravelTime[Location.valueOf(this.dLocation).getIndex()][Location.valueOf(this.aLocation).getIndex()] != 0)
            return this.getDTime().plusMinutes(StaticData.delay + StaticData.TravelTime[Location.valueOf(this.dLocation).getIndex()][Location.valueOf(this.aLocation).getIndex()]);
        else
            return this.getDTime().plusMinutes(StaticData.delay + StaticData.TravelTime[Location.valueOf(this.aLocation).getIndex()][Location.valueOf(this.dLocation).getIndex()]);

    }

    @Override//Override toString method returns string for print trip if tour exists or not
    public String toString() {
        String s = null;
        if (tour != null) {
            s = "Trip  " +
                    "tripId=" + tripId +
                    ", departure Location='" + dLocation + '\'' +
                    ", arrival Location='" + aLocation + '\'' +
                    ", Departure Time=" + DTime +
                    ", price=" + price +
                    ", available Seats=" + numSeats +
                    ", tour id=" + tour.getTourId() +
                    '\n';
            return s;
        } else {
            s = "Trip  " +
                    "tripId=" + tripId +
                    ", departure Location='" + dLocation + '\'' +
                    ", arrival Location='" + aLocation + '\'' +
                    ", Departure Time=" + DTime +
                    ", price=" + price +
                    ", available Seats=" + numSeats +
                    '\n';
            return s;

        }
    }
}
