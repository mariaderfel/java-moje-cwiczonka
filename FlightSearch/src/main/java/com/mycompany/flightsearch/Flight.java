/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flightsearch;

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
    
}
