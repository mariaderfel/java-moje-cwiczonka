/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kata;

import java.util.Random;

/**
 *
 * @author Marysia
 */
public class TakeTenMinutesWalk {

    public char[] walkPlan;

    public TakeTenMinutesWalk() {
        this.walkPlan = createWalk();
    }

    public boolean isWalkOk() {
        int we = 0, ns = 0;
        if (walkPlan.length != 10) {
            return false;
        }
        for (char direction : walkPlan) {
            if (direction=='w') {
                we++;
            }
            if (direction=='e') {
                we--;
            }
            if (direction=='n') {
                ns++;
            }
            if (direction=='s') {
                ns--;
            }
        }
        return (we == 0 && ns == 0);
    }
    
    private char [] createWalk(){
        Random liczba = new Random();
        int i = liczba.nextInt(11);
        char walk[] = new char[i];
        char[] direction ={'w', 'e', 's', 'n'};

        for(int j=0; j<i ; j++ ){
            walk[j] = direction[liczba.nextInt(4)];
            System.out.println(" | " + walk[j]);
        }
        return walk;
    }
    
    public static void main(String[] args) {
        
        TakeTenMinutesWalk walk = new TakeTenMinutesWalk();
        System.out.println( walk.isWalkOk()); 
    }
}
