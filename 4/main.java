package org.example;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Wing wing = new Wing(28.5, "Металл");
        Chassis chassis = new Chassis(3, true);
        Engine engine = new Engine("Турбореактивный", 15000);

        Airplane airplane = new Airplane(
                "Boeing 737",
                wing,
                chassis,
                engine
        );

        airplane.setRoute("Москва", "Санкт-Петербург");

        System.out.println(airplane);

        airplane.fly();
        airplane.printRoute();
    }
}
