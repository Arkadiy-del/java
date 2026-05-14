package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.OptionalInt;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

class Parking {
    private final BlockingQueue<Integer> freePlaces;
    private final ConcurrentHashMap<Integer, Car> occupiedPlaces;

    public Parking(int placeCount) {
        freePlaces = new ArrayBlockingQueue<>(placeCount);
        occupiedPlaces = new ConcurrentHashMap<>();

        for (int i = 1; i <= placeCount; i++) {
            freePlaces.add(i);
        }
    }

    public OptionalInt takePlace(Car car, long maxWaitingTimeMillis)
            throws InterruptedException {

        print(car.getCarNumber() + " приехал на стоянку и ищет место.");

        Integer placeNumber = freePlaces.poll(
                maxWaitingTimeMillis,
                TimeUnit.MILLISECONDS
        );

        if (placeNumber == null) {
            print(car.getCarNumber()
                    + " не дождался свободного места и уехал на другую стоянку.");
            return OptionalInt.empty();
        }

        occupiedPlaces.put(placeNumber, car);

        print(car.getCarNumber()
                + " занял машиноместо №" + placeNumber);

        return OptionalInt.of(placeNumber);
    }

    public void leavePlace(int placeNumber, Car car) {
        occupiedPlaces.remove(placeNumber);
        freePlaces.offer(placeNumber);

        print(car.getCarNumber()
                + " освободил машиноместо №" + placeNumber);
    }

    public static void print(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("[" + LocalTime.now().format(formatter) + "] " + message);
    }
}
