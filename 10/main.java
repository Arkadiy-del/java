package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необходимо указать входной и выходной файлы.");
            System.out.println("Пример: java Main input.txt output.txt");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                String result = findWordsStartingWithVowel(line);

                writer.write("Строка " + lineNumber + ": " + result);
                writer.newLine();

                lineNumber++;
            }

            System.out.println("Обработка завершена. Результат записан в файл: " + outputFile);

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }

    private static String findWordsStartingWithVowel(String line) {
        String[] words = line.split("[\\s,.;:!?()\"«»]+");

        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty() && startsWithVowel(word)) {
                result.append(word).append(" ");
            }
        }

        if (result.length() == 0) {
            return "слов, начинающихся с гласной буквы, нет";
        }

        return result.toString().trim();
    }

    private static boolean startsWithVowel(String word) {
        char firstLetter = Character.toLowerCase(word.charAt(0));

        return firstLetter == 'а'
                || firstLetter == 'е'
                || firstLetter == 'ё'
                || firstLetter == 'и'
                || firstLetter == 'о'
                || firstLetter == 'у'
                || firstLetter == 'ы'
                || firstLetter == 'э'
                || firstLetter == 'ю'
                || firstLetter == 'я'
                || firstLetter == 'a'
                || firstLetter == 'e'
                || firstLetter == 'i'
                || firstLetter == 'o'
                || firstLetter == 'u'
                || firstLetter == 'y';
    }
}
