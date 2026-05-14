package org.example;

import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class Car extends Thread {
    private final String carNumber;
    private final Parking parking;
    private final long maxWaitingTimeMillis;
    private final long parkingTimeMillis;

    public Car(String carNumber,
               Parking parking,
               long maxWaitingTimeMillis,
               long parkingTimeMillis) {
        this.carNumber = carNumber;
        this.parking = parking;
        this.maxWaitingTimeMillis = maxWaitingTimeMillis;
        this.parkingTimeMillis = parkingTimeMillis;
    }

    public String getCarNumber() {
        return carNumber;
    }

    @Override
    public void run() {
        try {
            int arrivalDelay = ThreadLocalRandom.current().nextInt(500, 2000);
            TimeUnit.MILLISECONDS.sleep(arrivalDelay);

            OptionalInt place = parking.takePlace(this, maxWaitingTimeMillis);

            if (place.isPresent()) {
                TimeUnit.MILLISECONDS.sleep(parkingTimeMillis);
                parking.leavePlace(place.getAsInt(), this);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Parking.print(carNumber + " был прерван.");
        }
    }

    @Override
    public String toString() {
        return "Автомобиль{" +
                "номер='" + carNumber + '\'' +
                ", максимальное время ожидания=" + maxWaitingTimeMillis +
                " мс, время стоянки=" + parkingTimeMillis +
                " мс}";
    }
}
