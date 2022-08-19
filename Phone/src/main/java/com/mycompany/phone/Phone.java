/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phone;

/**
 *
 * @author Marysia
 */
public class Phone {

    String weight;
    int price;

    public Phone(String weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public void checkPrice() {
        if (this.price < 200) {
            System.out.println("price is very good");
        } else if (this.price >= 200 && this.price < 299) {
            System.out.println("price is good");
        } else if (this.price >= 300 && this.price < 549) {
            System.out.println("price could be lower");
        } else if (this.price > 550) {
            System.out.println("this phone is too expensive");
        }
    }

    public void displayEventNumber(int min, int max) {
        for (int i = min; i < max; i++) {
            if (i % 2 == 0) {
                System.out.println("i: " + i);
            }
        }
    }

    public void liczbyPodzielne(int min, int max) {
        for (int i = min; i <= max; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                System.out.println(i + " ");
            }
        }
    }

    public void getEvenSum(int min, int max) {
        int sum = 0;
        for (int i = min; i <= max; i++) {
            if (i % 2 == 0) {
                sum = sum + i;
            }
        }
        System.out.println("SUM = " + sum);
    }
    
    public void getNumber(int a, int b){
        int i=0, min=0;
        if(a>=b){
            i=a;
            min =b;            
        } else {
            i=b;
            min=a;
        }
        for(int j=i; j>=min; j--){
            System.out.println(j + " ");
        }
    }
}
