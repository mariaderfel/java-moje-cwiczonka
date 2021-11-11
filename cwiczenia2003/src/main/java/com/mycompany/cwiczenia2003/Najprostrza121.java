/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cwiczenia2003;

/**
 *
 * @author Marysia
 */
public class Najprostrza121 {

    public static void main(String[] args) {
        System.out.println("znowu w siodle");

        if(args.length>0){
            int i=0;
            do{
                System.out.println("argument " + i + " = " + args[i]);
                i++;
            }while(args.length>i);
        }
        else{
            System.out.println("Wywołanie bez argumentów.");
        }
        String text1 = "Marysia";
        String text2 = "jest jak";
        int liczba = 1000000;
        System.out.println(text1 + " " + text2 + " " + liczba + " dolarów");

    }

}
