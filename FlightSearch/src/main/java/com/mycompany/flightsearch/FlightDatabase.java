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
        this.flights.add(new Flight("Praga", "Berlin", 1000));
        this.flights.add(new Flight("Miami", "Waszyngton", 1200));
        this.flights.add(new Flight("Praga", "Berlin", 800));
        this.flights.add(new Flight("Waszyngton", " Sidney", 1300));
        this.flights.add(new Flight("Praga", "Wiedeń", 900));
        this.flights.add(new Flight("Sidney", "Wiedeń", 400));
        this.flights.add(new Flight("Wiedeń", "Praga", 2000));
        this.flights.add(new Flight("Praga", "Miami", 7450));
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
        ArrayList<Flight> result = new ArrayList<>();
        Flight flight;
        for (int i = 0; i < this.flights.size(); i++) {
            flight = this.flights.get(i);
            if (flight.departure.equals(city) || flight.arrival.equals(city)) {
                if (!result.contains(flight)) {
                    result.add(flight);
                }
            }
        }
        return result;
    }

    private ArrayList<Flight> getFlihgtToCity(String city) {
        ArrayList<Flight> flightsList = new ArrayList<>();
        for (Flight flight : flightsList) {
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

    public void findCheapestFlight() {
        Flight cheapestFlight = null;
        ArrayList<Flight> flightsList = this.flights;
        for (Flight flight : flightsList) {
            if (cheapestFlight == null || !cheapestFlight.isCheaper(flight)) {
                cheapestFlight = flight;
            }
        }
        System.out.println("Najtańszy lot to: " + cheapestFlight.toString());
    }

}
