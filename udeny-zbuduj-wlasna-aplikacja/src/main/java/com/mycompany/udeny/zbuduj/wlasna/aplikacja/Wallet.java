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
public class Wallet {
    private float money;

    public Wallet() {
        this.money = 3000;
    }
    
    public void addMoney(float kwota){
        this.money += kwota;
    }
    
    public void minMoney(float kwota){
        this.money -= kwota;
    }
    
    public void basicInfo(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Wallet{" + "money=" + money + '}';
    }
    
    
    
}
