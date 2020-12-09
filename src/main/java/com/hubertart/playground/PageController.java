package com.hubertart.playground;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class PageController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/tasks")
    public String getTasks() {
        return "These are tasks";
    }

    @PostMapping("/tasks")
    public String createTask(){
        return "You just POSTed to /tasks";
    }

    @GetMapping("/math/pi")
    public String getPie(){
        return "" + Math.PI;
    }

    @GetMapping("/math/calculate")
    public String performOperation(MathService mathService){
        return mathService.performOperation();
    }

//    @PostMapping("/math/sum")
//    public String performSummation(@RequestParam Integer[] n){
//        int total = 0;
//        String output = "";
//        for( int i : n){
//            output += i + " + ";
//            total += i;
//        }
//        return output.substring(0, output.length() - 2) + "= " + total;
//    }

    @PostMapping("/math/sum")
    public String performSummation(MathService mathService){
        return mathService.performSummation();
    }

    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String performVolumeCalc(MathService mathService){
        return mathService.performVolumeCalc();
    }

    @PostMapping("/math/area")
    public String performAreaCalc(MathService mathService){
        return mathService.performAreaCalc();
    }

    @GetMapping("/flights/flight")
    public Flight getFlight(){
        Flight flight1 = new Flight();
        Flight.Ticket ticket1 = new Flight.Ticket();
        Flight.Ticket.Passenger passenger1 = new Flight.Ticket.Passenger();
        passenger1.setFirstname("Mary");
        passenger1.setLastName("Jane");
        ticket1.setPassenger(passenger1);
        ticket1.setPrice(200);
        flight1.setDeparts(new GregorianCalendar(2020, Calendar.DECEMBER, 9, 14, 34).getTime());
        flight1.setTickets(Arrays.asList(ticket1));

        return flight1;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights(){
        Flight flight1 = new Flight();
        Flight.Ticket ticket1 = new Flight.Ticket();
        Flight.Ticket.Passenger passenger1 = new Flight.Ticket.Passenger();
        passenger1.setFirstname("Mary");
        passenger1.setLastName("Jane");
        ticket1.setPassenger(passenger1);
        ticket1.setPrice(200);
        flight1.setDeparts(new GregorianCalendar(2020, Calendar.DECEMBER, 9, 14, 34).getTime());
        flight1.setTickets(Arrays.asList(ticket1));

        Flight flight2 = new Flight();
        Flight.Ticket ticket2 = new Flight.Ticket();
        Flight.Ticket.Passenger passenger2 = new Flight.Ticket.Passenger();
        passenger2.setFirstname("Spider");
        passenger2.setLastName("Man");
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(10);
        flight2.setDeparts(new GregorianCalendar(2020, Calendar.DECEMBER, 9, 14, 34).getTime());
        flight2.setTickets(Arrays.asList(ticket2));

        return Arrays.asList(flight1, flight2);
    }

    @PostMapping("/flights/tickets/total")
    public String getStrTotalTwoFlights(@RequestBody Flight flights){
        return "{\n    \"result\": " + (flights.getTickets().get(0).getPrice() + flights.getTickets().get(1).getPrice()) + "\n}";
    }
}
