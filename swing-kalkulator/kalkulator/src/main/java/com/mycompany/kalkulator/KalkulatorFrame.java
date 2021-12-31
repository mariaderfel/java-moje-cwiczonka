/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kalkulator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Marysia
 */
public class KalkulatorFrame extends JFrame {
    private JLabel wynikLabel = new JLabel("0", JLabel.RIGHT);
    private boolean start;
    private double wynik;
    public KalkulatorFrame() {
        start = true;
        
        this.add(wynikLabel, BorderLayout.NORTH);
        
        this.add(utworzPanelZGuzikami(), BorderLayout.CENTER);        
        
        this.setSize(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private JPanel utworzPanelZGuzikami() {
        JPanel panelGuzikow = new JPanel();
        panelGuzikow.setLayout(new GridLayout(4, 4));
        
        String[] guziki = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", " ", "=", "+"
        };
        
        for (String g : guziki) { 
            panelGuzikow.add(utworzGuzik(g));
        }
        return panelGuzikow;
    }
    
    private JButton utworzGuzik(String text) {
        JButton guzik = new JButton(text);
        guzik.addActionListener(calkListener);
        return guzik;
    }
    
    private ActionListener calkListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
           //wziąć obiekt
           JButton obiektButton = (JButton) event.getSource();
           //wziac tekst z obiektu
           String tekstButton = obiektButton.getText();
            //indexOf bierze znak (stringa) z tekstButton i szuka go w tym stringu który jest ptxrd ktopką. 
            //jak znajdzie to zwraca index (numer miejsca liczony od zera) na którym znajdzie pierwszy znak z string atextButton
            if("/*-+=".indexOf(tekstButton)>=0 ){
                calculate(tekstButton);
            }
            else{
                wypiszLiczby(tekstButton);
            }
        }
    };
            
    private void calculate(String text){
        // to co siedzi w wynik.tekst trzeba zmienić na double i zapisać do zmiennej
        Double liczba = Double.parseDouble(wynikLabel.getText());
        //napisać obsługę poszczegunych znaków - działań
        
        //+
        if(text.equals("+")) wynik += liczba;
        //zapisac to co jest w wynik.tekst do zmiennej
        //zapisać znak w zmiennej znak
        
        //-,*,/
        //tak samo
        
        // = 
        //wykonac działanie zapisane w zmiennej znak, na liczbach:
        if(text.equals("+")) wynik = liczba;
        //wynik.tekst i objeckButton.getText
        wynikLabel.setText(""+wynik);
       
    }
    
    private void wypiszLiczby(String text){
        if(start) {
            wynikLabel.setText("");
            start=false;
        }
        wynikLabel.setText(wynikLabel.getText()+text);
    }
    
        
    
}
