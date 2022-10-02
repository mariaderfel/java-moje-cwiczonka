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
public class Car {
    private String model;
    private String brand;
    private String color;

    public Car(String model, String brand, String color) {
        this.model = model;
        this.brand = brand;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void basicInfo(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString() {
        return "Car{" + "model=" + model + ", brand=" + brand + ", color=" + color + '}';
    }
    
    
    
}
