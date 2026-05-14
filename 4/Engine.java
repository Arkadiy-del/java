package org.example;

import java.util.Objects;

class Engine {
    private String type;
    private int power;

    public Engine() {
    }

    public Engine(String type, int power) {
        this.type = type;
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engine)) return false;
        Engine engine = (Engine) o;
        return power == engine.power
                && Objects.equals(type, engine.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, power);
    }

    @Override
    public String toString() {
        return "Двигатель{" +
                "тип='" + type + '\'' +
                ", мощность=" + power +
                '}';
    }
}
