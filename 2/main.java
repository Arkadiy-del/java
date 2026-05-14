package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество чисел n: ");
        int n = scanner.nextInt();

        String[] numbers = new String[n];
        int[] lengths = new int[n];

        int sumLength = 0;

        System.out.println("Введите " + n + " чисел:");

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.next();

            // Если число отрицательное, знак минус не считаем
            if (numbers[i].startsWith("-")) {
                lengths[i] = numbers[i].length() - 1;
            } else {
                lengths[i] = numbers[i].length();
            }

            sumLength += lengths[i];
        }

        double averageLength = (double) sumLength / n;

        System.out.println("\nСредняя длина чисел: " + averageLength);

        System.out.println("\nЧисла, длина которых меньше средней:");
        for (int i = 0; i < n; i++) {
            if (lengths[i] < averageLength) {
                System.out.println(numbers[i] + " — длина: " + lengths[i]);
            }
        }

        System.out.println("\nЧисла, длина которых больше средней:");
        for (int i = 0; i < n; i++) {
            if (lengths[i] > averageLength) {
                System.out.println(numbers[i] + " — длина: " + lengths[i]);
            }
        }

        scanner.close();
    }
}

//variant B
package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите n: ");
        double n = scanner.nextDouble();

        System.out.print("Введите m: ");
        double m = scanner.nextDouble();

        System.out.print("Введите k: ");
        double k = scanner.nextDouble();

        System.out.println();

        if (k > n && k <= m) {
            System.out.println("k принадлежит интервалу (n, m]");
        } else {
            System.out.println("k не принадлежит интервалу (n, m]");
        }

        if (k >= n && k < m) {
            System.out.println("k принадлежит интервалу [n, m)");
        } else {
            System.out.println("k не принадлежит интервалу [n, m)");
        }

        if (k > n && k < m) {
            System.out.println("k принадлежит интервалу (n, m)");
        } else {
            System.out.println("k не принадлежит интервалу (n, m)");
        }

        if (k >= n && k <= m) {
            System.out.println("k принадлежит интервалу [n, m]");
        } else {
            System.out.println("k не принадлежит интервалу [n, m]");
        }

        scanner.close();
    }
}

//variant C
package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();

        int[][] a = new int[n][n];

        // Заполнение матрицы случайными числами от -n до n
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = random.nextInt(2 * n + 1) - n;
            }
        }

        // Вывод матрицы
        System.out.println("\nМатрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
            System.out.println();
        }

        int maxIncrease = 1;
        int maxDecrease = 1;

        int currentIncrease = 1;
        int currentDecrease = 1;

        // Предыдущий элемент
        int previous = a[0][0];

        // Обход матрицы слева направо по строкам
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Первый элемент уже взяли как previous
                if (i == 0 && j == 0) {
                    continue;
                }

                int current = a[i][j];

                // Проверка возрастающей последовательности
                if (current > previous) {
                    currentIncrease++;
                } else {
                    currentIncrease = 1;
                }

                // Проверка убывающей последовательности
                if (current < previous) {
                    currentDecrease++;
                } else {
                    currentDecrease = 1;
                }

                if (currentIncrease > maxIncrease) {
                    maxIncrease = currentIncrease;
                }

                if (currentDecrease > maxDecrease) {
                    maxDecrease = currentDecrease;
                }

                previous = current;
            }
        }

        System.out.println("\nНаибольшее количество возрастающих элементов подряд: " + maxIncrease);
        System.out.println("Наибольшее количество убывающих элементов подряд: " + maxDecrease);

        scanner.close();
    }
}
