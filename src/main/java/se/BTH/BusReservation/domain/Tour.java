package se.BTH.BusReservation.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity // entity tour will create table with same name
public class Tour {
    //data fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer tourId;// primary key
    private String firstLocation;
    private String lastLocation;
    private LocalDate dDate;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "tour")
    private List<Trip> trips;// the relationship is every tour has many trips

    //constructors
    public Tour(String firstLocation, String lastLocation, List<Trip> trips) {
        this();
        this.firstLocation = firstLocation;
        this.lastLocation = lastLocation;
        this.trips = trips;

    }

    public Tour() {
     dDate=LocalDate.now();
    }

    //getter and setter for fields
    public Integer getTourId() {
        return tourId;
    }

    public String getFirstLocation() {
        return firstLocation;
    }

    public void setFirstLocation(String firstLocation) {
        this.firstLocation = firstLocation;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    public LocalDate getdDate() {
        return dDate;
    }

    public void setdDate(LocalDate dDate) {
        this.dDate = dDate;
    }

    //searchTripBydL method returns index of trip that departure location equals input parameter and returns -1 if does not found
    public Integer searchTripBydL(String dLocation){
        for (Trip t:trips) {
            if(t.getdLocation().equals(dLocation))
            return trips.indexOf(t);

        }return -1;
    }

    //searchTripBydL method returns index of trip that arrival location equals input parameter and returns -1 if does not found
    public Integer searchTripByaL(String aLocation){
        for (Trip t:trips) {
            if(t.getaLocation().equals(aLocation))
                return trips.indexOf(t);

        }return -1;
    }

    //directPath method returns list of trips that go from  dL to aL
    public List<Trip> directPath(String dL, String aL){
        List<Trip> subTrip=new ArrayList<>();
        int x=searchTripBydL(dL);
        int y=searchTripByaL(aL);
        if ((y!=-1)&&(x<=y)) {
            for (int i = x; i <= y; ++i) {
                subTrip.add(trips.get(i));
            }
        }return subTrip ;
    }

    @Override // override toString method
    public String toString() {
        return "Tour{" +
                "tourId=" + tourId +
                ", firstLocation='" + firstLocation + '\'' +
                ", lastLocation='" + lastLocation + '\'' +
                ", departure Date=" + dDate +"\n"+
                ", trips=" + trips +
                '}';
    }
}
