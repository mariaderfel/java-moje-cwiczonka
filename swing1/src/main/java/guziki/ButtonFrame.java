/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guziki;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Marysia
 * 
 * ta klasa tworzy RAMKĘ 
 * z panelem z guzikami'
 * do guzików jest dadana akcja co ma się dziać po kliknięciu
 */
public class ButtonFrame extends JFrame {

    // stworzyć panel
    private JPanel panel= new JPanel();

    // długość i szerokośc
    private final int WIDTH = 300;
    private final int HEIGHT = 200;

    //konstruktor
    public ButtonFrame() {
        
        //przypisanie rozmiaru ramki i wycentrowanie
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        
        //deklaracja guzikow
        JButton zoltyButon = new JButton("Żółty");
        JButton niebieskiButton = new JButton("Niebieski");
        JButton czewonyButton = new JButton("Czerwony");
        
        //dodanie guzików do panelu
        panel.add(zoltyButon);
        panel.add(niebieskiButton);
        panel.add(czewonyButton);
        
        //dodanie panelu do ramki
        add(panel);
        
        //obsługa kliknieci w guzik
        
        //utworzenie akcji dla guzika
        ButtonAction zoltyAction = new ButtonAction(panel, Color.YELLOW);
        //dodanie do guzika nasłuchiwadza z akcją do wykonania
        zoltyButon.addActionListener(zoltyAction);
        
        ButtonAction niebAction = new ButtonAction(panel, Color.BLUE);
        niebieskiButton.addActionListener(niebAction);
        
        ButtonAction czerwonyAction = new ButtonAction(panel, Color.RED);
        czewonyButton.addActionListener(czerwonyAction);
    }
}
