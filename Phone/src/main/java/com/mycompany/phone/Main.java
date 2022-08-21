/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phone;

import java.util.ArrayList;

/**
 *
 * @author Marysia
 */
public class Main {
    public static void main(String[] args) {
        Phone phone = new Phone("200", 700);
        
//        phone.displayEventNumber(-1,20);
        
//        phone.liczbyPodzielne(4, 18);
        
//        phone.getEvenSum(0,11);
        
//        phone.getNumber(11,16);
    
        ArrayList<String> list = new ArrayList<String>();
        
        list.add("test");
        list.add("mouse");
        
//        System.out.println("LISTA: " + list);
        
        String element = list.get(1);
        
        System.out.println("Element: " + element);
    }
}
