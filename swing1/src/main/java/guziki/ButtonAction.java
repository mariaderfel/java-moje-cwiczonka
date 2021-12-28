/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guziki;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Marysia
 */
public class ButtonAction implements ActionListener
{
    private JPanel panel;
    private Color backgroundColor;

    public ButtonAction(JPanel panel, Color color) {
        backgroundColor = color;
        this.panel = panel;
    }
    

    @Override
    public void actionPerformed(ActionEvent event) {
        panel.setBackground(backgroundColor);
    }
    
}
