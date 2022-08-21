/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flightsearch;

import java.util.ArrayList;

/**
 *
 * @author Marysia
 */
public class Main {
    public static void main(String[] args) {
        FlightDatabase flightDatabase = new FlightDatabase();
        
//        Flight flight = new Flight("Miami", "Waszyngton");
//        flightDatabase.checkIfFlightExists("Miami", "Waszyngton");
//        flightDatabase.checkIfFlightExists("Miami", "Praga");
//        flightDatabase.checkIfFlightExistsVoid("Miami", "Waszyngton");      
//        flightDatabase.findFlihgtToCity("Praga");

        flightDatabase.displayFlights(flightDatabase.findCity("Praga"));
        
        
    }
    
}
