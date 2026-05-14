package org.example;

import java.util.Objects;

class Airplane {
    private String model;
    private Wing wing;
    private Chassis chassis;
    private Engine engine;
    private String departurePoint;
    private String destinationPoint;

    public Airplane() {
    }

    public Airplane(String model, Wing wing, Chassis chassis, Engine engine) {
        this.model = model;
        this.wing = wing;
        this.chassis = chassis;
        this.engine = engine;
    }

    public void fly() {
        if (engine != null && chassis != null) {
            System.out.println("Самолет " + model + " летит.");
        } else {
            System.out.println("Самолет не может лететь: отсутствуют необходимые части.");
        }
    }

    public void setRoute(String departurePoint, String destinationPoint) {
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
    }

    public void printRoute() {
        if (departurePoint == null || destinationPoint == null) {
            System.out.println("Маршрут не задан.");
        } else {
            System.out.println("Маршрут самолета: " + departurePoint + " — " + destinationPoint);
        }
    }

    public String getModel() {
        return model;
    }

    public Wing getWing() {
        return wing;
    }

    public Chassis getChassis() {
        return chassis;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setWing(Wing wing) {
        this.wing = wing;
    }

    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airplane)) return false;
        Airplane airplane = (Airplane) o;
        return Objects.equals(model, airplane.model)
                && Objects.equals(wing, airplane.wing)
                && Objects.equals(chassis, airplane.chassis)
                && Objects.equals(engine, airplane.engine)
                && Objects.equals(departurePoint, airplane.departurePoint)
                && Objects.equals(destinationPoint, airplane.destinationPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, wing, chassis, engine, departurePoint, destinationPoint);
    }

    @Override
    public String toString() {
        return "Самолет{" +
                "модель='" + model + '\'' +
                ", крыло=" + wing +
                ", шасси=" + chassis +
                ", двигатель=" + engine +
                ", пункт отправления='" + departurePoint + '\'' +
                ", пункт назначения='" + destinationPoint + '\'' +
                '}';
    }
}
