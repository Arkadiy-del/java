package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.OptionalInt;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking(3);

        Car[] cars = {
                new Car("A001AA", parking, 2000, 4000),
                new Car("B002BB", parking, 3000, 3000),
                new Car("C003CC", parking, 1000, 5000),
                new Car("D004DD", parking, 2500, 2000),
                new Car("E005EE", parking, 1500, 3500),
                new Car("F006FF", parking, 3000, 2500),
                new Car("G007GG", parking, 1000, 2000)
        };

        for (Car car : cars) {
            car.start();
        }

        for (Car car : cars) {
            try {
                car.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Главный поток был прерван.");
            }
        }

        System.out.println("\nРабота автостоянки завершена.");
    }
}
