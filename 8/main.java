package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите текст:");
        String text = scanner.nextLine();

        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == 'Р' && chars[i + 1] == 'А') {
                chars[i + 1] = 'О';
            } else if (chars[i] == 'Р' && chars[i + 1] == 'а') {
                chars[i + 1] = 'о';
            } else if (chars[i] == 'р' && chars[i + 1] == 'а') {
                chars[i + 1] = 'о';
            } else if (chars[i] == 'р' && chars[i + 1] == 'А') {
                chars[i + 1] = 'О';
            }
        }

        String correctedText = new String(chars);

        System.out.println("Исправленный текст:");
        System.out.println(correctedText);

        scanner.close();
    }
}
