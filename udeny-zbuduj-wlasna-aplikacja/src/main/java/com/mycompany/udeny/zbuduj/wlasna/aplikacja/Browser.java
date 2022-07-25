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
public interface Browser {
    
    static  final String ENGINE = "WebKit";
    
    default String getEngine(){
        return Browser.ENGINE;
    }
    
    void gotoPage(String url);
    void refreshPage();
    void bookmarkPage();
    
    
    
}
