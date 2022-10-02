/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kata;

/**
 *
 * @author Marysia
 */
public class Solution {
     public static String whoLikesIt(String... names) {
               int ilosc = names.length;
        String info;
        switch (ilosc){
            case 0: {
                info = "no one likes this";
                break;
            }
            case 1:{
                info = (names[0] + " likes this");
                break;
            }
            case 2:{
                info = (names[0] + " and " + names[1]+ " like this");
                break;
            }
            case 3 :{
                info = (names[0] +", " + names[1] + " and " + names[2]+ " like this");
                break;
            }
            default:{
               info = (names[0] +", " + names[1] + " and " + (ilosc-2) + " others like this");
                break;
            }
                
        }
        return info;
    }
     
     public static void main(String[] args) {
         Solution.whoLikesIt("Alex", "Jacob", "Mark", "Max");
    }
}

