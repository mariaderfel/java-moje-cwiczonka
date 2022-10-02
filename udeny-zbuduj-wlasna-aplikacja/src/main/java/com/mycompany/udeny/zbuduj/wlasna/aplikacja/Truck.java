/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.udeny.zbuduj.wlasna.aplikacja;

/**
 *
 * @author Marysia
 */
public class Truck {
    private String producent;
    private String model;
    private int iloscKol;
    private String kolor;

    private final int topSpeed = 100;
    private final int acceleration;
    
    public Truck() {
        this.producent = "Freightliner";
        this.model = "9664";
        this.iloscKol = 6;
        this.acceleration = 60;
    }

    public Truck(String kolor) {
        this();
        this.kolor = kolor;    
    }

    public void printInfo(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString() {
        return "Truck{" + "producent=" + producent + ", model=" + model + ", iloscKol=" + iloscKol + ", kolor=" + kolor + '}';
    }
    
    
}
