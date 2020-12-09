package com.hubertart.playground;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Flight {

    private Date departs;
    private List<Ticket> ticket;

    public void setDeparts(Date departs){ this.departs = departs; }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date getDeparts(){ return departs; }

    public void setTicket(List<Ticket> ticket){ this.ticket = ticket; }
    public List<Ticket> getTicket(){ return ticket; }

    static class Ticket {
        private Passenger passenger;
        private int price;

        public void setPassenger(Passenger passenger){ this.passenger = passenger; }
        public Passenger getPassenger() { return passenger; }

        public void setPrice(int price){ this.price = price; }
        public int getPrice(){ return price; }

        static class Passenger {
            private String firstName;
            private String lastName;

            public void setFirstname(String firstName){ this.firstName = firstName; }
            public String getFirstName(){ return firstName; }

            public void setLastName(String lastName){ this.lastName = lastName; }
            public String getLastName(){ return lastName; }
        }
    }

}


