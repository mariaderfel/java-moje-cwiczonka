/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marysia
 */
public class Prostokat {

    double x;
    double y;

    public Prostokat(double x, double y) {
        this.x = x;
        this.y = y;
    }
     
    public double pole(){
        return this.x*this.y;
    }
    
    public double obwod(){
        return 2*(x+y);
    }
    
    public double przekatna(){
        return Math.sqrt(x*x + y*y);
    }
    
    public static void main(String args[]) {
        Prostokat prostokat = new Prostokat(3,4);
        
        System.out.println("pole: " + prostokat.pole());
        System.out.println("obwod: " + prostokat.obwod());
        System.out.println("przek¹tna: " + prostokat.przekatna());
    }
}
