package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    private static final String DEFAULT_INPUT_FILE = "input.txt";

    public static void main(String[] args) {
        String inputFile;

        if (args.length > 0) {
            inputFile = args[0];
        } else {
            inputFile = DEFAULT_INPUT_FILE;
        }

        try {
            List<Double> numbers = readNumbersFromFile(inputFile);

            double sum = calculateSum(numbers);
            double average = sum / numbers.size();

            System.out.println("Прочитанные числа:");
            for (double number : numbers) {
                System.out.println(number);
            }

            System.out.println("\nКоличество чисел: " + numbers.size());
            System.out.println("Сумма: " + sum);
            System.out.println("Среднее значение: " + average);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + inputFile);

        } catch (InvalidNumberRecordException e) {
            System.out.println("Ошибка в данных файла: " + e.getMessage());

        } catch (OutOfMemoryError e) {
            System.out.println("Недостаточно памяти для обработки файла.");

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());

        } catch (ArithmeticException e) {
            System.out.println("Ошибка вычислений: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
        }
    }

    private static List<Double> readNumbersFromFile(String fileName)
            throws IOException, InvalidNumberRecordException {

        List<Double> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String firstLine = reader.readLine();

            if (firstLine == null || firstLine.trim().isEmpty()) {
                throw new InvalidNumberRecordException(
                        "Отсутствует первая строка с количеством чисел N."
                );
            }

            int n = parseCount(firstLine);

            if (n <= 0) {
                throw new InvalidNumberRecordException(
                        "Количество чисел N должно быть больше 0."
                );
            }

            String line;

            for (int i = 1; i <= n; i++) {
                line = reader.readLine();

                if (line == null) {
                    throw new InvalidNumberRecordException(
                            "Отсутствует запись для числа №" + i
                    );
                }

                double number = parseNumberRecord(line, i);
                numbers.add(number);
            }
        }

        return numbers;
    }

    private static int parseCount(String line) throws InvalidNumberRecordException {
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            throw new InvalidNumberRecordException(
                    "Первая строка должна содержать целое число N.",
                    e
            );
        }
    }

    private static double parseNumberRecord(String line, int lineNumber)
            throws InvalidNumberRecordException {

        String[] parts = line.split(";", 2);

        if (parts.length != 2) {
            throw new InvalidNumberRecordException(
                    "Некорректная запись в строке №" + lineNumber
                            + ". Ожидаемый формат: локаль;число"
            );
        }

        String localeText = parts[0].trim();
        String numberText = parts[1].trim();

        if (localeText.isEmpty()) {
            throw new InvalidNumberRecordException(
                    "В строке №" + lineNumber + " отсутствует локаль."
            );
        }

        if (numberText.isEmpty()) {
            throw new InvalidNumberRecordException(
                    "В строке №" + lineNumber + " отсутствует число."
            );
        }

        Locale locale = createLocale(localeText, lineNumber);

        return parseLocalizedNumber(numberText, locale, lineNumber);
    }

    private static Locale createLocale(String localeText, int lineNumber)
            throws InvalidNumberRecordException {

        Locale locale = Locale.forLanguageTag(localeText.replace('_', '-'));

        if (locale.getLanguage().isEmpty()) {
            throw new InvalidNumberRecordException(
                    "Некорректная локаль в строке №" + lineNumber + ": " + localeText
            );
        }

        return locale;
    }

    private static double parseLocalizedNumber(String numberText, Locale locale, int lineNumber)
            throws InvalidNumberRecordException {

        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        numberFormat.setGroupingUsed(true);

        ParsePosition position = new ParsePosition(0);
        Number parsedNumber = numberFormat.parse(numberText, position);

        if (parsedNumber == null) {
            throw new InvalidNumberRecordException(
                    "Значение в строке №" + lineNumber
                            + " не является числом: " + numberText
            );
        }

        if (position.getIndex() != numberText.length()) {
            throw new InvalidNumberRecordException(
                    "Некорректная запись числа в строке №" + lineNumber
                            + ": " + numberText
            );
        }

        double value = parsedNumber.doubleValue();

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new InvalidNumberRecordException(
                    "Число в строке №" + lineNumber
                            + " выходит за пределы допустимого значения: " + numberText
            );
        }

        if (Math.abs(value) > Double.MAX_VALUE) {
            throw new InvalidNumberRecordException(
                    "Число в строке №" + lineNumber
                            + " превышает максимально допустимое значение."
            );
        }

        return value;
    }

    private static double calculateSum(List<Double> numbers) {
        double sum = 0;

        for (double number : numbers) {
            sum += number;

            if (Double.isInfinite(sum) || Double.isNaN(sum)) {
                throw new ArithmeticException(
                        "Сумма вышла за пределы допустимых значений."
                );
            }
        }

        return sum;
    }
}
