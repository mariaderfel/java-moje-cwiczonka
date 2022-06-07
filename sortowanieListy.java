
import com.sun.net.httpserver.Authenticator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marysia
 */
public class sortowanieListy {

    public static void main(String args[]) {
        sortowanieListy obiektKlasy = new sortowanieListy();
//        isPitagoras(5, 6, 7);
//        System.out.println(obiektKlasy.pierwiastek(64));
//        System.out.println(obiektKlasy.doTrzeciej(5));
//        System.out.println(obiektKlasy.podzielny3i5(23));
        obiektKlasy.dzialania(112,24);

    }

    private static void isPitagoras(int x, int y, int z) {
        List<Integer> odcinki = Arrays.asList(x, y, z);
        Collections.sort(odcinki);

        int a = odcinki.get(0);
        int b = odcinki.get(1);
        int c = odcinki.get(2);
        if (a * a + b * b == c * c) {
            System.out.println("Z odciknów " + a + " , " + b + " , " + c + " MO¯NA zbudowaæ trójk¹t prostok¹tny ");
        } else {
            System.out.println("Z odciknów " + a + " , " + b + " , " + c + " NIE MO¯NA zbudowaæ trójk¹ta prostok¹tnego ");
        }
    }

    private double pierwiastek(double x) {
        return Math.sqrt(x);
    }

    private double doTrzeciej(double x) {
        return Math.pow(x, 3);
    }

    private boolean podzielny3i5(double x) {
        return (x % 3 == 0 && x % 5 == 0);
    }
    
    private void dzialania(double x, double y){
        System.out.println(x + "  + " +  y + " = " + (x+y));
        System.out.println(x + "  - " +  y + " = " + (x-y));
        System.out.println(x + "  * " +  y + " = " + (x*y));
        System.out.println(x + "  / " +  y + " = " + (x/y));   
    }
}
