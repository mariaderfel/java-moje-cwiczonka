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
public class FlightDatabase {

    private ArrayList<Flight> flights = new ArrayList<Flight>();

    public FlightDatabase() {
        this.flights.add(new Flight("Praga", "Berlin"));
        this.flights.add(new Flight("Miami", "Waszyngton"));
        this.flights.add(new Flight("Praga", "Berlin"));
        this.flights.add(new Flight("Waszyngton", " Sidney"));
        this.flights.add(new Flight("Praga", "Wiedeń"));
        this.flights.add(new Flight("Sidney", "Wiedeń"));
        this.flights.add(new Flight("Wiedeń", "Praga"));
        this.flights.add(new Flight("Praga", "Miami"));
    }

    public boolean checkIfFlightExists(String departure, String arrival) {
        for (int i = 0; i < this.flights.size(); i++) {
            Flight flight = this.flights.get(i);
            if (flight.departure.equals(departure) && flight.arrival.equals(arrival)) {
                System.out.println("Lot z " + departure + " do " + arrival + " ISTNIEJE");
                return true;
            }
        }
        System.out.println("Lot z " + departure + " do " + arrival + " NIE ISTNIEJE");
        return false;
    }

    public void checkIfFlightExistsVoid(String departure, String arrival) {
        for (int i = 0; i < this.flights.size(); i++) {
            Flight flight = this.flights.get(i);
            if (flight.departure.equals(departure) && flight.arrival.equals(arrival)) {
                System.out.println("Lot z " + departure + " do " + arrival + " ISTNIEJE");
                return;
            }
        }
        System.out.println("Lot z " + departure + " do " + arrival + " NIE ISTNIEJE");
    }

    public void getFlihgtFtomCity(String city) {
        boolean flightNotExist = true;
        for (int i = 0; i < this.flights.size(); i++) {
            Flight flight = this.flights.get(i);
            if (flight.departure.equals(city)) {
                System.out.println(flight.toString());
                flightNotExist = false;
            }
        }
        if (flightNotExist) {
            System.out.println("Nie ma lotu z miasta: " + city);
        }
    }

    public void findFlihgtToCity(String city) {
        ArrayList<Flight> result = getFlihgtToCity(city);
        displayFlights(result);
    }

    public ArrayList<Flight> findCity(String city) {
        ArrayList<Flight> result =new ArrayList<>();
        Flight flight;
        for (int i = 0; i < this.flights.size(); i++) {
            flight = this.flights.get(i);
            if (flight.departure.equals(city) || flight.arrival.equals(city)) {
                if(!result.contains(flight)){
                    result.add(flight);
                }
            }
        }
        return result;
    }

    private ArrayList<Flight> getFlihgtToCity(String city) {
        ArrayList<Flight> flightsList = new ArrayList<>();
        for (int i = 0; i < this.flights.size(); i++) {
            Flight flight = this.flights.get(i);
            if (flight.arrival.equals(city)) {
                flightsList.add(flight);
            }
        }
        return flightsList;
    }

    public void displayFlights(ArrayList<Flight> flightsList) {
        if (flightsList.isEmpty()) {
            System.out.println("Nie znaleziono szukanego lotu");
            return;
        }
        for (int i = 0; i < flightsList.size(); i++) {
            System.out.println(flightsList.get(i).toString());
        }
    }

}
