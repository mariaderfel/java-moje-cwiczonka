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
public class Tv {
    private String producent;
    private String model;
    private String nrSeryjny;
    private int cale;

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNrSeryjny() {
        return nrSeryjny;
    }

    public void setNrSeryjny(String nrSeryjny) {
        this.nrSeryjny = nrSeryjny;
    }

    public int getCale() {
        return cale;
    }

    public void setCale(int cale) {
        this.cale = cale;
    }
    
    
    public void printBasicData(){
        System.out.println("Tv{" + "producent=" + producent + ", model=" + model + ", nrSeryjny=" + nrSeryjny + ", cale=" + cale + '}');
    }

    
}
