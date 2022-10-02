/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.udeny.zbuduj.wlasna.aplikacja;

import java.util.Random;

/**
 *
 * @author Marysia
 */
public class CarFactory {
        
    public Car buildCar(){
        String[] colors = {"czerwony", "czarny", "niebieski"};
        String color;
        Random liczba = new Random();
        color=colors[liczba.nextInt(3)];
        Car car = new Car("Ford", "Mustang", color );
        return car;
    }
    
}
