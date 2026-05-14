package org.example;

import java.util.Objects;

class Chassis {
    private int wheelCount;
    private boolean released;

    public Chassis() {
    }

    public Chassis(int wheelCount, boolean released) {
        this.wheelCount = wheelCount;
        this.released = released;
    }

    public int getWheelCount() {
        return wheelCount;
    }

    public boolean isReleased() {
        return released;
    }

    public void setWheelCount(int wheelCount) {
        this.wheelCount = wheelCount;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chassis)) return false;
        Chassis chassis = (Chassis) o;
        return wheelCount == chassis.wheelCount
                && released == chassis.released;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wheelCount, released);
    }

    @Override
    public String toString() {
        return "Шасси{" +
                "количество колес=" + wheelCount +
                ", выпущено=" + released +
                '}';
    }
}
