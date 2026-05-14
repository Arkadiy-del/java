package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите текст. Для завершения введите END:");

        StringBuilder input = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).equals("END")) {
            input.append(line).append("\n");
        }

        TextbookText text = TextParser.parse(input.toString());

        System.out.println("\nИсправленный текст:");
        System.out.println(text);

        Word uniqueWord = text.findWordFromFirstSentenceNotInOthers();

        System.out.println("\nРезультат:");

        if (uniqueWord == null) {
            System.out.println("Такого слова нет.");
        } else {
            System.out.println("Слово из первого предложения, которого нет в остальных: "
                    + uniqueWord.getValue());
        }

        scanner.close();
    }
}
