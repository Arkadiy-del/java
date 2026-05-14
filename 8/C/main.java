package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите текст:");
        String text = scanner.nextLine();

        text = normalizeSpaces(text);

        String result = removeMaxSubstring(text);

        System.out.println("Исходный текст:");
        System.out.println(text);

        System.out.println("Текст после удаления подстроки:");
        System.out.println(result);

        scanner.close();
    }

    private static String normalizeSpaces(String text) {
        return text.replaceAll("[\\t ]+", " ").trim();
    }

    private static String removeMaxSubstring(String text) {
        Map<Character, Integer> firstIndexMap = new HashMap<>();

        int maxStart = -1;
        int maxEnd = -1;
        int maxLength = 0;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (!firstIndexMap.containsKey(currentChar)) {
                firstIndexMap.put(currentChar, i);
            } else {
                int start = firstIndexMap.get(currentChar);
                int length = i - start + 1;

                if (length > maxLength) {
                    maxLength = length;
                    maxStart = start;
                    maxEnd = i;
                }
            }
        }

        if (maxStart == -1) {
            return text;
        }

        return text.substring(0, maxStart) + text.substring(maxEnd + 1);
    }
}
