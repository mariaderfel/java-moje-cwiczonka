/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package light;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Marysia
 */
public class LightAction implements ActionListener{

    //zmienne
//    ramka i kolor
    private JPanel panel;
    private Color kolor;
    
    //konstruktor
    public LightAction(JPanel panel, Color kolor) {    
        this.panel = panel;
        this.kolor = kolor;
        
        
    }

    //metoda actionPerformaed z interfejsu
    @Override
    public void actionPerformed(ActionEvent event) {
        panel.setBackground(kolor);
    }
    
}
