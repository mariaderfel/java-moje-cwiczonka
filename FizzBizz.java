/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marysia
 */
public class FizzBizz {

    public static void main(String args[]) {
        FizzBizz fizzBizz=new FizzBizz();
         fizzBizz.printFizzBuzz1(33);
    }

    private void printFizzBuzz1(int max) {
        for (Integer i = 1; i <= max; i++) {
           if(i%3==0 && i%5!=0)
                System.out.println(i+ ": Fizz");
           if(i%5==0 && i%3!=0)
                System.out.println(i+ ": Beez");
           if(i%3==0 && i%5==0)
                System.out.println(i+ ":FizzBizz");
           if(i%3!=0 && i%5!=0)
                System.out.println(i.toString());
        }  
    }
    
    private void printFizzBuzz2(int max){
        for (Integer i = 1; i <= max; i++) {
           if(i%3==0){
               if(i%5==0)
                System.out.println(i+ ": FizzBeez");
               else
                   System.out.println(i+ ": Fizz");
           }  
           else if(i%5==0 && i%3!=0)
                System.out.println(i+ ": Beez");
           else
                System.out.println(i.toString());
        }  
    }
    
private static void fizzBuzzInJava8(int num) {
//    IntStream.rangeClosed(1, 100)
//        .mapToObj(i -> i % 5 == 0 ? (i % 7 == 0 ? "FizzBuzz" : "Fizz") : (i % 7 == 0 ? "Buzz" : i))
//        .forEach(System.out::println);
  }
}
