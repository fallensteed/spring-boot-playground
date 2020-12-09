package com.hubertart.playground;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flight {

    private Date departs;
    private List<Ticket> tickets;

    public void setDeparts(Date departs){ this.departs = departs; }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
//    @JsonProperty("Departs")
    public Date getDeparts(){ return departs; }

    public void setTickets(List<Ticket> tickets){ this.tickets = tickets; }
//    @JsonProperty("Ticket")
    public List<Ticket> getTickets(){ return tickets; }

    static class Ticket {
        private Passenger passenger;
        private int price;

        public void setPassenger(Passenger passenger){ this.passenger = passenger; }
//        @JsonProperty("Passenger")
        public Passenger getPassenger() { return passenger; }

        public void setPrice(int price){ this.price = price; }
//        @JsonProperty("Price")
        public int getPrice(){ return price; }

        static class Passenger {
            private String firstName;
            private String lastName;

            public void setFirstname(String firstName){ this.firstName = firstName; }
//            @JsonProperty("FirstName")
            public String getFirstName(){ return firstName; }

            public void setLastName(String lastName){ this.lastName = lastName; }
//            @JsonProperty("LastName")
            public String getLastName(){ return lastName; }
        }
    }

}


