package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<QuadraticEquation> equations = new ArrayList<>();

        System.out.print("Введите количество квадратных уравнений: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nУравнение №" + (i + 1));

            double a;
            do {
                System.out.print("Введите a, a != 0: ");
                a = scanner.nextDouble();

                if (a == 0) {
                    System.out.println("Коэффициент a не должен быть равен 0.");
                }
            } while (a == 0);

            System.out.print("Введите b: ");
            double b = scanner.nextDouble();

            System.out.print("Введите c: ");
            double c = scanner.nextDouble();

            equations.add(new QuadraticEquation(a, b, c));
        }

        System.out.println("\nИнформация об уравнениях:");
        for (QuadraticEquation equation : equations) {
            System.out.println("\n" + equation);
            equation.printRoots();
            equation.printExtremum();
            equation.printIntervals();
        }

        if (equations.size() >= 2) {
            QuadraticEquation q1 = equations.get(0);
            QuadraticEquation q2 = equations.get(1);

            System.out.println("\nАрифметические действия над первыми двумя уравнениями:");
            System.out.println("Первое: " + q1);
            System.out.println("Второе: " + q2);

            System.out.println("Сложение: " + q1.add(q2));
            System.out.println("Вычитание: " + q1.subtract(q2));
            System.out.println("Умножение: " + q1.multiply(q2));

            DivisionResult result = q1.divide(q2);
            System.out.println("Деление:");
            System.out.println(result);
        }

        Double minRoot = null;
        Double maxRoot = null;

        for (QuadraticEquation equation : equations) {
            List<Double> roots = equation.getRoots();

            for (double root : roots) {
                if (minRoot == null || root < minRoot) {
                    minRoot = root;
                }

                if (maxRoot == null || root > maxRoot) {
                    maxRoot = root;
                }
            }
        }

        System.out.println("\nНаименьший и наибольший корни среди всех уравнений:");

        if (minRoot == null) {
            System.out.println("Действительных корней нет.");
        } else {
            System.out.println("Наименьший корень: " + minRoot);
            System.out.println("Наибольший корень: " + maxRoot);
        }

        scanner.close();
    }
}
