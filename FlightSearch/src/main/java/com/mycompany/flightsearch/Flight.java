/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flightsearch;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Marysia
 */
public class Flight {

    String departure;
    String arrival;

    public Flight(String departure, String arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    public void flightInfo(Flight flight){
        System.out.println("Departure: " + this.departure + ", Arrival: "+ this.arrival);
    }
       
    @Override
    public String toString() {
        return "Flight{" + "departure=" + departure + ", arrival=" + arrival + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flight other = (Flight) obj;
        if (!Objects.equals(this.departure, other.departure)) {
            return false;
        }
        if (!Objects.equals(this.arrival, other.arrival)) {
            return false;
        }
        return true;
    }
    
    

    
    
}
