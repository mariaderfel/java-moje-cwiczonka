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
public class Roadster {

    float topSpeed;
    float accteleration;
    float crasyTopSpeed;
    float crasyAcceleration;
    boolean crasymode;

    public Roadster(float topSpeed, float accteleration) {
        this.topSpeed = topSpeed;
        this.accteleration = accteleration;
    }

    public Roadster(float topSpeed, float accteleration, float crasyTopSpeed, float crasyAcceleration) {
        this(topSpeed, accteleration);
        this.crasyTopSpeed = crasyTopSpeed;
        this.crasyAcceleration = crasyAcceleration;
        this.crasymode = true;
    }

    public float getTopSpeed() {
        return topSpeed;
    }

    public float getAccteleration() {
        return accteleration;
    }

    public float getTopSpeed(boolean crasymode) {
        return crasymode ? this.crasyTopSpeed : this.topSpeed;
    }

    public float getAccteleration(boolean crasymode) {
        return crasymode ? this.crasyAcceleration : this.accteleration;
    }

}
