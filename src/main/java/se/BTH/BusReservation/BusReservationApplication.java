package se.BTH.BusReservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import se.BTH.BusReservation.domain.*;
import se.BTH.BusReservation.services.CustomerService;
import se.BTH.BusReservation.services.ReservationService;
import se.BTH.BusReservation.services.TourService;
import se.BTH.BusReservation.services.TripService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootApplication
public class BusReservationApplication {
    //Data fields
    static int key = 0;
    static Tour FirstTour;
    static String FL, LL;
    static Map<Integer, List<Trip>> options = new HashMap<>();
    static boolean connection = false;
    static List<String> location = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static List<Trip> trips = new ArrayList<>();
    static List<Tour> tours = new ArrayList<>();
    TourService tourService = new TourService();
    TripService tripService = new TripService();


    public static void main(String[] args) {
        //  SpringApplication.run(BusReservationApplication.class, args);
        ApplicationContext applicationContext = SpringApplication.run(BusReservationApplication.class, args);
        CustomerService cService = applicationContext.getBean(CustomerService.class);
        TripService tripService = applicationContext.getBean(TripService.class);
        TourService tourService = applicationContext.getBean(TourService.class);
        ReservationService reserService = applicationContext.getBean(ReservationService.class);
        System.out.println("Welcome to Bus Reservation System");
        System.out.println("--------------------------------------");
        saveData(cService, tripService, tourService, reserService);
        do {
            menu();
            int x = input.nextInt();
            switch (x) {
                case 1://Add customer
                    addCustomers(cService);
                    break;
                case 2://view customer
                    if (!cService.getAllCustomers().isEmpty()) {
                        System.out.println("The customers are :");
                        cService.getAllCustomers().forEach(System.out::println);
                    }
                    break;
                case 3://search customer by national Id
                    System.out.println("enter 4 digit national id for find customer");
                    int national = input.nextInt();
                    if (!(cService.getCustomerByNational(national).isEmpty()))
                        cService.getCustomerByNational(national).forEach(System.out::println);
                    else
                        System.out.println("Customer not found");
                    System.out.println();
                    break;
                case 4://search customer by name
                    System.out.println("enter customer name for find customer");
                    String name = input.next();
                    System.out.println();
                    if (!(cService.getCustomerByName(name).isEmpty()))
                        cService.getCustomerByName(name).forEach(System.out::println);
                    else
                        System.out.println("Customer not found");
                    System.out.println();
                    break;
                case 5://search customer by email
                    System.out.println("enter customer email for find customer");
                    String email = input.next();
                    System.out.println();
                    if (!(cService.getCustomerByEmail(email).isEmpty()))
                        cService.getCustomerByEmail(email).forEach(System.out::println);
                    else
                        System.out.println("Customer not found");
                    System.out.println();
                    break;
                case 6://Add trip
                    addTrip(tripService);
                    break;
                case 7://view trips
                    if (!tripService.getTrips().isEmpty()) {
                        System.out.println("The trips are :");
                        tripService.getTrips().forEach(System.out::println);
                    } else
                        System.out.println("there are no trips");
                    break;
                case 8://search trips by departure Location
                    System.out.println("enter departure Location");
                    String dl = input.next().toUpperCase();
                    if (!(tripService.getTripByDLocation(dl).isEmpty())) {
                        System.out.println("The trips that departing from  " + dl + " are :");
                        tripService.getTripByDLocation(dl).forEach(System.out::println);
                    } else
                        System.out.println("There is no trip from this site " + dl);
                    break;
                case 9://search trips by arrival Location
                    System.out.println("enter arrival Location");
                    String al = input.next().toUpperCase();
                    if (!(tripService.getTripByALocation(al).isEmpty())) {
                        System.out.println("The trips that reach to  " + al + " are :");
                        tripService.getTripByALocation(al).forEach(System.out::println);
                    } else
                        System.out.println("There is no trip to this site " + al);
                    break;
                case 10://search trip by departure and arrival Locations
                    System.out.println("enter departure Location");
                    dl = input.next().toUpperCase();
                    System.out.println("enter arrival Location");
                    al = input.next().toUpperCase();
                    if (!(tripService.serchTrip(dl, al).isEmpty())) {
                        System.out.println("the trips that take off from  " + dl + " and reach " + al + " are :");
                        tripService.serchTrip(dl, al).forEach(System.out::println);
                    } else
                        System.out.println("There is no trip from this location " + dl + " to this location " + al);
                    System.out.println();
                    break;
                case 11://Add tour
                    addTourTrips(tripService, tourService);
                    break;
                case 12://view all tours
                    if (!tourService.getAllTours().isEmpty()) {
                        System.out.println(" All tour are : ");
                        tourService.getAllTours().forEach(System.out::println);
                    } else
                        System.out.println("there are no tours");
                    break;
                case 13://search tour by first and last Locations
                    System.out.println("Enter first location");
                    String fl = input.next();
                    System.out.println("Enter last location");
                    String ll = input.next();
                    if (!tourService.searchTour(fl, ll).isEmpty()) {
                        System.out.println("the tours that take off from  " + fl + " and reach " + ll + " are :");
                        tourService.searchTour(fl, ll).forEach(System.out::println);
                    } else
                        System.out.println("The tour is not found");
                    System.out.println();
                    break;
                case 14://Book a ticket for a specific route between two locations
                    reservation(cService, tripService, tourService, reserService);
                    break;
                case 15://view All reservations
                    if (!reserService.getAllReservation().isEmpty()) {
                        System.out.println("All reservations are :");
                        reserService.getAllReservation().forEach(System.out::println);
                    } else
                        System.out.println("there are not reservations");
                    System.out.println();
                    break;
                case 0://Exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong selected number ");
            }
        } while (true);
    }

//    @Bean
//    public CommandLineRunner demo() {
//        return (args) -> {
//            saveData();
//            addCustomers();
//            addTourTrips();
//
//        };
    // }

    private static void menu() {
        System.out.println("select from menu:");
        System.out.println("1.Add customer");
        System.out.println("2.view all customers");
        System.out.println("3.search customer by national Id");
        System.out.println("4.search customer by name");
        System.out.println("5.search customer by email");
        System.out.println("6.Add trip");
        System.out.println("7.view all trips ");
        System.out.println("8.search trips by departure Location");
        System.out.println("9.search trips by arrival Location");
        System.out.println("10.search trip by departure and arrival Locations");
        System.out.println("11.Add tour");
        System.out.println("12.view all tours");
        System.out.println("13.search tour by first and last Locations ");
        System.out.println("14.Book a ticket for a specific route between two locations");
        System.out.println("15.view all reservations");
        System.out.println("0.Exit");
    }

    private static void saveData(CustomerService cService, TripService tripService, TourService tourService, ReservationService reserService) {
        Customer c1 = new Customer("Elin", 1111, "elin@gmail.com");
        cService.addCustomer(c1);
        Customer c2 = new Customer("shatha", 2222, "shatha@gmail.com");
        cService.addCustomer(c2);
        Customer c3 = new Customer("saleh", 3333, "saleh@gmail.com");
        cService.addCustomer(c3);
        Customer c4 = new Customer("Erik", 4444, "erik@gmail.com");
        cService.addCustomer(c4);
        cService.addCustomer(new Customer("Saleh", 6666, "saleh@bth.se"));

        Trip trip1 = new Trip("A", "I", LocalTime.of(8, 0, 0), 20);
        Trip trip2 = new Trip(trip1.getaLocation(), "K", Trip.calcTimeD(trip1.getdLocation(), trip1.getaLocation(), trip1.getDTime()), 20);
        Trip trip3 = new Trip(trip2.getaLocation(), "M", Trip.calcTimeD(trip2.getdLocation(), trip2.getaLocation(), trip2.getDTime()), 30);
        Trip trip4 = new Trip(trip3.getaLocation(), "N", Trip.calcTimeD(trip3.getdLocation(), trip3.getaLocation(), trip3.getDTime()), 50);

        ArrayList<Trip> trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        tripService.addTrips(trips);

        Tour tour1 = new Tour(trip1.getdLocation(), trip4.getaLocation(), trips);
        tourService.addTour(tour1);
        trip1.setTour(tour1);
        trip2.setTour(tour1);
        trip3.setTour(tour1);
        trip4.setTour(tour1);
        tripService.uppdateTrip(trip1);
        tripService.uppdateTrip(trip2);
        tripService.uppdateTrip(trip3);
        tripService.uppdateTrip(trip4);

        trip1 = new Trip("A", "K", LocalTime.of(9, 0, 0), 25);
        trip2 = new Trip(trip1.getaLocation(), "B", Trip.calcTimeD(trip1.getdLocation(), trip1.getaLocation(), trip1.getDTime()), 22);
        trip3 = new Trip(trip2.getaLocation(), "M", Trip.calcTimeD(trip2.getdLocation(), trip2.getaLocation(), trip2.getDTime()), 50);
        trip4 = new Trip(trip3.getaLocation(), "C", Trip.calcTimeD(trip3.getdLocation(), trip3.getaLocation(), trip3.getDTime()), 20);

        trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        tripService.addTrips(trips);
        tour1 = new Tour(trip1.getdLocation(), trip4.getaLocation(), trips);
        tourService.addTour(tour1);
        trip1.setTour(tour1);
        trip2.setTour(tour1);
        trip3.setTour(tour1);
        trip4.setTour(tour1);
        tripService.uppdateTrip(trip1);
        tripService.uppdateTrip(trip2);
        tripService.uppdateTrip(trip3);
        tripService.uppdateTrip(trip4);

        trip1 = new Trip("A", "K", LocalTime.of(9, 0, 0), 25);
        trip2 = new Trip(trip1.getaLocation(), "B", Trip.calcTimeD(trip1.getdLocation(), trip1.getaLocation(), trip1.getDTime()), 22);
        trip3 = new Trip(trip2.getaLocation(), "M", Trip.calcTimeD(trip2.getdLocation(), trip2.getaLocation(), trip2.getDTime()), 50);
        trip4 = new Trip(trip3.getaLocation(), "C", Trip.calcTimeD(trip3.getdLocation(), trip3.getaLocation(), trip3.getDTime()), 20);
        trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        tripService.addTrips(trips);
        tour1 = new Tour(trip1.getdLocation(), trip4.getaLocation(), trips);
        tourService.addTour(tour1);
        trip1.setTour(tour1);
        trip2.setTour(tour1);
        trip3.setTour(tour1);
        trip4.setTour(tour1);
        tripService.uppdateTrip(trip1);
        tripService.uppdateTrip(trip2);
        tripService.uppdateTrip(trip3);
        tripService.uppdateTrip(trip4);

        trip1 = new Trip("B", "C", LocalTime.of(9, 0, 0), 25);
        trip2 = new Trip(trip1.getaLocation(), "F", Trip.calcTimeD(trip1.getdLocation(), trip1.getaLocation(), trip1.getDTime()), 22);
        trip3 = new Trip(trip2.getaLocation(), "K", Trip.calcTimeD(trip2.getdLocation(), trip2.getaLocation(), trip2.getDTime()), 50);
        trip4 = new Trip(trip3.getaLocation(), "N", Trip.calcTimeD(trip3.getdLocation(), trip3.getaLocation(), trip3.getDTime()), 20);

        trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        tripService.addTrips(trips);
        tour1 = new Tour(trip1.getdLocation(), trip4.getaLocation(), trips);
        tourService.addTour(tour1);
        trip1.setTour(tour1);
        trip2.setTour(tour1);
        trip3.setTour(tour1);
        trip4.setTour(tour1);
        tripService.uppdateTrip(trip1);
        tripService.uppdateTrip(trip2);
        tripService.uppdateTrip(trip3);
        tripService.uppdateTrip(trip4);

        trip1 = new Trip("B", "H", LocalTime.of(5, 0, 0), 30);
        trip2 = new Trip(trip1.getaLocation(), "K", Trip.calcTimeD(trip1.getdLocation(), trip1.getaLocation(), trip1.getDTime()), 22);
        trip3 = new Trip(trip2.getaLocation(), "M", Trip.calcTimeD(trip2.getdLocation(), trip2.getaLocation(), trip2.getDTime()), 40);
        trip4 = new Trip(trip3.getaLocation(), "N", Trip.calcTimeD(trip3.getdLocation(), trip3.getaLocation(), trip3.getDTime()), 25);

        trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        tripService.addTrips(trips);

        tour1 = new Tour(trip1.getdLocation(), trip4.getaLocation(), trips);
        tourService.addTour(tour1);
        trip1.setTour(tour1);
        trip2.setTour(tour1);
        trip3.setTour(tour1);
        trip4.setTour(tour1);
        tripService.uppdateTrip(trip1);
        tripService.uppdateTrip(trip2);
        tripService.uppdateTrip(trip3);
        tripService.uppdateTrip(trip4);

        trip1 = new Trip("I", "G", LocalTime.of(7, 0, 0), 25);
        trip2 = new Trip(trip1.getaLocation(), "D", Trip.calcTimeD(trip1.getdLocation(), trip1.getaLocation(), trip1.getDTime()), 22);
        trip3 = new Trip(trip2.getaLocation(), "F", Trip.calcTimeD(trip2.getdLocation(), trip2.getaLocation(), trip2.getDTime()), 50);
        trip4 = new Trip(trip3.getaLocation(), "E", Trip.calcTimeD(trip3.getdLocation(), trip3.getaLocation(), trip3.getDTime()), 20);

        trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        tripService.addTrips(trips);

        tour1 = new Tour(trip1.getdLocation(), trip4.getaLocation(), trips);
        tourService.addTour(tour1);
        trip1.setTour(tour1);
        trip2.setTour(tour1);
        trip3.setTour(tour1);
        trip4.setTour(tour1);
        tripService.uppdateTrip(trip1);
        tripService.uppdateTrip(trip2);
        tripService.uppdateTrip(trip3);
        tripService.uppdateTrip(trip4);

        trip1 = new Trip("E", "B", LocalTime.of(13, 0, 0), 25);
        trip2 = new Trip(trip1.getaLocation(), "L", Trip.calcTimeD(trip1.getdLocation(), trip1.getaLocation(), trip1.getDTime()), 22);
        trip3 = new Trip(trip2.getaLocation(), "N", Trip.calcTimeD(trip2.getdLocation(), trip2.getaLocation(), trip2.getDTime()), 50);
        trip4 = new Trip(trip3.getaLocation(), "K", Trip.calcTimeD(trip3.getdLocation(), trip3.getaLocation(), trip3.getDTime()), 20);

        trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        tripService.addTrips(trips);

        tour1 = new Tour(trip1.getdLocation(), trip4.getaLocation(), trips);
        tourService.addTour(tour1);
        trip1.setTour(tour1);
        trip2.setTour(tour1);
        trip3.setTour(tour1);
        trip4.setTour(tour1);
        tripService.uppdateTrip(trip1);
        tripService.uppdateTrip(trip2);
        tripService.uppdateTrip(trip3);
        tripService.uppdateTrip(trip4);

        FL = "C";
        LL = "F";
        findPath(tripService, tourService, FL, LL, location);
        connection = false;
        Reservation res = new Reservation(cService.getCustomerByNational(1111).get(0), options.get(0), tourService.getSeat(options.get(0)));
        reserService.addReservation(res);

        FL = "K";
        LL = "N";
        key = 0;
        options = new HashMap<>();
        connection = false;
        location = new ArrayList<>();
        findPath(tripService, tourService, FL, LL, location);
        res = new Reservation(cService.getCustomerByNational(6666).get(0), options.get(3), tourService.getSeat(options.get(3)));
        reserService.addReservation(res);

        FL = "K";
        LL = "N";
        key = 0;
        options = new HashMap<>();
        connection = false;
        location = new ArrayList<>();
        findPath(tripService, tourService, FL, LL, location);
        res = new Reservation(cService.getCustomerByNational(4444).get(0), options.get(1), tourService.getSeat(options.get(1)));
        reserService.addReservation(res);
    }

    private static void findPath(TripService tripService, TourService tourService, String dL, String aL, List<String> location) {
        trips = tripService.getTripByDLocation(dL);
        List<Trip> directTrip = tripService.serchTrip(dL, aL);
        List<Tour> directTour = new ArrayList<>();
        if (!directTrip.isEmpty()) {
            if (!connection)
                options.put(key++, directTrip);//step1 add direct trip
            for (Trip temp : directTrip) {
                directTour.add(temp.getTour());
            }
        }
        tours = new ArrayList<>();// delete direct trip from trip list
        if (!(trips.isEmpty()) && (!(directTour.isEmpty())))
            for (int i = 0; i < trips.size(); i++) {
                for (int j = 0; j < directTour.size(); j++) {
                    if (trips.get(i).getTour().getTourId() == directTour.get(j).getTourId())
                        trips.remove(trips.get(i));
                }
            }
        for (Trip temp : trips) {
            tours.add(temp.getTour());
        }
        List<Tour> tourforDelet = new ArrayList<>();
        if (!tours.isEmpty())
            for (Tour tour : tours) {
                List<Trip> subtrips = tour.directPath(dL, aL);
                if (!subtrips.isEmpty())
                    if (!connection) {
                        options.put(key++, subtrips);//step2 add direct path in tour
                        tourforDelet.add(tour);
                    } else {
                        List<Trip> preveiosList = FirstTour.directPath(FL, dL);
                        if (preveiosList.addAll(subtrips))
                            subtrips = preveiosList;
                        if ((tourService.isValidateOptions(subtrips)) && (tourService.isFreeSeat(subtrips)))
                            options.put(key++, subtrips);  //step3 add  path with one conection
                    }
            }
        for (int j = 0; j < trips.size(); j++) {
            for (Tour tour : tourforDelet) {
                if (trips.get(j).getTour().equals(tour))
                    trips.remove(trips.get(j));
            }
        }
        tours.clear();
        for (Trip temp : trips) {
            tours.add(temp.getTour());
        }
        for (Tour temp1 : tours) {
            if (!connection) {
                for (int i = temp1.searchTripBydL(dL); i < temp1.getTrips().size(); i++) {
                    location.add(temp1.getTrips().get(i).getaLocation());
                }
                FirstTour = temp1;
                connection = true;
                for (int i = 0; i < location.size(); i++) {
                    String mL = location.get(i);
                    findPath(tripService, tourService, mL, aL, location);
                    //  System.out.println(location);

                }
            }
        }
    }

    private static void addTrip(TripService tripService) {
        System.out.println("enter departure location");
        String dl = input.next().substring(0, 1).toUpperCase();
        System.out.println("enter arrival location");
        String al = input.next().substring(0, 1).toUpperCase();
        System.out.println("enter the houre for departure time ");
        int h = input.nextInt();
        System.out.println("enter the minutes for departure time ");
        int m = input.nextInt();
        System.out.println("enter the seconds for departure time ");
        int s = input.nextInt();
        System.out.println("enter price for this trip ");
        int price = input.nextInt();
        Trip t = new Trip(dl, al, LocalTime.of(h, m, s), price);
        tripService.addTrip(t);
        System.out.println("Trip was added successfully");
        System.out.print(t);
    }

    private static void addTourTrips(TripService tripService, TourService tourService) {
        System.out.println("enter first location for tour");
        String fl = input.next().substring(0, 1).toUpperCase();
        String dl = fl;
        System.out.println("enter arrival location for trip");
        String al = input.next().substring(0, 1).toUpperCase();
        System.out.println("enter the houre for departure time ");
        int h = input.nextInt();
        System.out.println("enter the minutes for departure time ");
        int m = input.nextInt();
        System.out.println("enter the seconds for departure time ");
        int s = input.nextInt();
        System.out.println("enter price for this trip ");
        int price = input.nextInt();
        Trip trip1 = new Trip(dl, al, LocalTime.of(h, m, s), price);
        List<Trip> trips = new ArrayList<>();
        trips.add(trip1);
        System.out.println("if you wanted to add new trip press 0");
        int x = input.nextInt();
        while (x == 0) {
            System.out.println("enter arrival location for next trip");
            al = input.next().substring(0, 1).toUpperCase();
            System.out.println("enter price for next trip ");
            price = input.nextInt();
            Trip trip2 = new Trip(trip1.getaLocation(), al, Trip.calcTimeD(trip1.getdLocation(), trip1.getaLocation(), trip1.getDTime()), price);
            trips.add(trip2);
            trip1 = trip2;
            System.out.println("if you wanted to add new trip press 0");
            x = input.nextInt();
        }
        tripService.addTrips(trips);
        Tour tour = new Tour(fl, al, trips);
        tourService.addTour(tour);
        System.out.println("Tour was added Successfully");
        System.out.println(tour);
    }

    private static void addCustomers(CustomerService cService) {
        System.out.println("enter name");
        String name = input.next();
        System.out.println("enter 4 digit for national");
        int national = input.nextInt();
        System.out.println("enter email");
        String email = input.next();
        System.out.println();
        Customer c = new Customer(name, national, email);
        cService.addCustomer(c);
        System.out.println("The customer was added to database successfully");
        System.out.println(c);
        System.out.println();
    }

    private static void reservation(CustomerService cService, TripService tripService, TourService tourService, ReservationService reserService) {
        key = 0;
        options = new HashMap<>();
        connection = false;
        location = new ArrayList<>();
        FL = "";
        LL = "";
        System.out.println("Enter national Id for customer");
        int national = input.nextInt();
        if (cService.getCustomerByNational(national).isEmpty())
            System.out.println("The customer is not found");
        else {
            System.out.println("Please enter departure location and arrival location (A --> N)");
            System.out.println("departure location =");
            FL = input.next().toUpperCase();
            System.out.println("arrival location =");
            LL = input.next().toUpperCase();
            System.out.println("the options between two location " + FL + " and " + LL + " are :");
            findPath(tripService, tourService, FL, LL, location);
            if (options.isEmpty()) {
                System.out.println("Unfortunately there are no options within the specified path");
            } else {
                for (int i = 0; i < options.size(); i++) {
                    int ii = i + 1;
                    System.out.println("Option number  " + ii);
                    System.out.println(options.get(i));
                }
                if (!options.isEmpty())
                    System.out.println("select option ");
                int optionNum = input.nextInt();
                trips = options.get(optionNum - 1);
                int seatNum = tourService.getSeat(trips);
                Customer customer = cService.getCustomerByNational(national).get(0);
                Reservation res = new Reservation(customer, trips, seatNum);
                reserService.addReservation(res);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
                LocalTime time = res.getReservTime().toLocalTime();
                String f = formatter.format(time);
                System.out.println("---------------------------------------------------------------");
                System.out.println("Information of Ticket no. " + res.getTicketNum() + "      Date " + res.getReservTime().toLocalDate() + "     kl. " + f);
                System.out.println("Customse name  :" + res.getCustomer().getName() + "      National id :" + res.getCustomer().getNational());
                if (tourService.isConection(res.getTrips()))
                    System.out.println("Departure location " + FL + "  Kl." + res.getTrips().get(0).getDTime() + "     Arrival location  " + LL + "  kl." +
                            res.getTrips().get(res.getTrips().size() - 1).calcArrivalTime() +
                            "\nyou will change your tour at location " + tourService.getConection(res.getTrips()) + "   Arrival Time   KL." + tourService.getATimeConection(trips) + " Departure time kl." + tourService.getDTimeConection(trips));
                else
                    System.out.println("Departure location " + FL + "  Kl." + res.getTrips().get(0).getDTime() + "    Arrival location  " + LL + "  kl." +
                            res.getTrips().get(res.getTrips().size() - 1).calcArrivalTime());
                System.out.println("The ticket's price is " + tourService.totalPrice(options.get(optionNum - 1)) + " $ ");
                System.out.println("---------------------------------------------------------------");
            }
        }
    }

}