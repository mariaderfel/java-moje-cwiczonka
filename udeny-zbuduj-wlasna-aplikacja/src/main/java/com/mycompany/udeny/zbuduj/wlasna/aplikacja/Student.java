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
public class Student {
    private String imie;
    private String nazwisko;
    private int wiek;

    public Student() {
        this.imie = "puste Imie";
        this.nazwisko = "puste Nazwisko";
        this.wiek = 20;
    }

    public Student(String imie, String nazwisko, int wiek) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
    }

    
    public void basicInfo(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString() {
        return "Student{" + "imie=" + imie + ", nazwisko=" + nazwisko + ", wiek=" + wiek + '}';
    }
    
    
    
}
