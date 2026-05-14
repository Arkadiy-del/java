package org.example;

import java.util.ArrayList;
import java.util.List;

class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    public QuadraticEquation() {
        this.a = 1;
        this.b = 0;
        this.c = 0;
    }

    public QuadraticEquation(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("Коэффициент a не должен быть равен 0");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public QuadraticEquation(QuadraticEquation other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
    }

    public void setA(double a) {
        if (a == 0) {
            throw new IllegalArgumentException("Коэффициент a не должен быть равен 0");
        }

        this.a = a;
    }

    public double getA() {
        return a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getB() {
        return b;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getC() {
        return c;
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public List<Double> getRoots() {
        List<Double> roots = new ArrayList<>();

        double d = getDiscriminant();

        if (d > 0) {
            double x1 = (-b - Math.sqrt(d)) / (2 * a);
            double x2 = (-b + Math.sqrt(d)) / (2 * a);

            roots.add(x1);
            roots.add(x2);
        } else if (d == 0) {
            double x = -b / (2 * a);
            roots.add(x);
        }

        return roots;
    }

    public void printRoots() {
        double d = getDiscriminant();

        System.out.println("Дискриминант: " + d);

        List<Double> roots = getRoots();

        if (roots.isEmpty()) {
            System.out.println("Действительных корней нет.");
        } else if (roots.size() == 1) {
            System.out.println("Один корень: x = " + roots.get(0));
        } else {
            System.out.println("Корни: x1 = " + roots.get(0) + ", x2 = " + roots.get(1));
        }
    }

    public double getValue(double x) {
        return a * x * x + b * x + c;
    }

    public void printExtremum() {
        double x0 = -b / (2 * a);
        double y0 = getValue(x0);

        if (a > 0) {
            System.out.println("Экстремум: минимум");
        } else {
            System.out.println("Экстремум: максимум");
        }

        System.out.println("Вершина параболы: x = " + x0 + ", y = " + y0);
    }

    public void printIntervals() {
        double x0 = -b / (2 * a);

        if (a > 0) {
            System.out.println("Убывает на интервале: (-∞; " + x0 + ")");
            System.out.println("Возрастает на интервале: (" + x0 + "; +∞)");
        } else {
            System.out.println("Возрастает на интервале: (-∞; " + x0 + ")");
            System.out.println("Убывает на интервале: (" + x0 + "; +∞)");
        }
    }

    public Polynomial toPolynomial() {
        return new Polynomial(new double[]{c, b, a});
    }

    public Polynomial add(QuadraticEquation other) {
        return this.toPolynomial().add(other.toPolynomial());
    }

    public Polynomial subtract(QuadraticEquation other) {
        return this.toPolynomial().subtract(other.toPolynomial());
    }

    public Polynomial multiply(QuadraticEquation other) {
        return this.toPolynomial().multiply(other.toPolynomial());
    }

    public DivisionResult divide(QuadraticEquation other) {
        return this.toPolynomial().divide(other.toPolynomial());
    }

    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c + " = 0";
    }
}
