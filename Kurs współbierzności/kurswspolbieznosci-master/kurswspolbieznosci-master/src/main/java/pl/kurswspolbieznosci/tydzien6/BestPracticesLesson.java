package pl.kurswspolbieznosci.tydzien6;

import java.time.LocalDateTime;

public class BestPracticesLesson {

    public static void main(String[] args) {
        print("Starting...");



        print("DONE");
    }


    static void print(Object output) {
        System.out.println(
            String.format("%s: %s - %s", LocalDateTime.now(), Thread.currentThread().getName(), output)
        );
    }
}
