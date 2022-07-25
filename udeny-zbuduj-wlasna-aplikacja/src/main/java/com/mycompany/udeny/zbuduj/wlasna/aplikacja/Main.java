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
public class Main {
    public static void main(String[] args) {
        Browser chrome = new Chrome();
        Browser firefox = new Firefox();
        
        String url = "wp.pl";
        
        chrome.gotoPage(url);
        System.out.println(chrome.getEngine());
        
        firefox.gotoPage("google.pl");
        
        SuperBrowser superCkrome = new SuperChrome();
        superCkrome.supergotoPage(url);
        
        Chrome simpleChrome = new Chrome();
        simpleChrome.gotoPage("onet.pl");
    }
}
