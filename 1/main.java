package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите количество случайных чисел: ");
        int n = scanner.nextInt();

        System.out.println("\nС переходом на новую строку:");
        for (int i = 0; i < n; i++) {
            int number = random.nextInt(100); // случайное число от 0 до 99
            System.out.println(number);
        }

        System.out.println("\nБез перехода на новую строку:");
        for (int i = 0; i < n; i++) {
            int number = random.nextInt(100);
            System.out.print(number + " ");
        }

        scanner.close();
    }
}

//variant B
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество чисел n: ");
        int n = scanner.nextInt();

        System.out.println("Введите " + n + " целых чисел:");

        System.out.println("Числа, которые делятся на 3 или на 9:");
        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();

            if (number % 3 == 0) {
                System.out.print(number + " ");
            }
        }

        scanner.close();
    }
}
