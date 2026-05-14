package org.example;

import java.util.Objects;

class Wing {
    private double length;
    private String material;

    public Wing() {
    }

    public Wing(double length, String material) {
        this.length = length;
        this.material = material;
    }

    public double getLength() {
        return length;
    }

    public String getMaterial() {
        return material;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wing)) return false;
        Wing wing = (Wing) o;
        return Double.compare(wing.length, length) == 0
                && Objects.equals(material, wing.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, material);
    }

    @Override
    public String toString() {
        return "Крыло{" +
                "длина=" + length +
                ", материал='" + material + '\'' +
                '}';
    }
}
