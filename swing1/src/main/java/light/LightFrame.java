/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package light;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Marysia
 */
public class LightFrame extends JFrame{
    
    private final int szerokosc =300;
    private final int wysokosc =200;
    
    public LightFrame(){
        setSize(szerokosc, wysokosc);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        
        JButton wlacz = new JButton("Włącz światło");
        JButton wylacz = new JButton("Wyłącz światło");
        
        LightAction wlaczAction = new LightAction(panel, Color.WHITE);
        wlacz.addActionListener(wlaczAction);
        
        LightAction wylaczAction = new LightAction(panel, Color.BLACK);
        wylacz.addActionListener(wylaczAction);
        
        
        
        panel.add(wlacz);
        panel.add(wylacz);
        
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
        
        
    }
    
}
