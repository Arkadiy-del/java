package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int DEFAULT_TIMEOUT = 1000;
    private static final int THREAD_COUNT = 50;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальный IP-адрес: ");
        String startIp = scanner.nextLine();

        System.out.print("Введите конечный IP-адрес: ");
        String endIp = scanner.nextLine();

        System.out.print("Введите таймаут проверки в мс, например 1000: ");
        int timeout = scanner.nextInt();

        if (timeout <= 0) {
            timeout = DEFAULT_TIMEOUT;
        }

        try {
            long start = ipToLong(startIp);
            long end = ipToLong(endIp);

            if (start > end) {
                System.out.println("Ошибка: начальный IP больше конечного IP.");
                return;
            }

            long count = end - start + 1;

            if (count > 65536) {
                System.out.println("Слишком большой диапазон. Укажите диапазон меньше.");
                return;
            }

            System.out.println("\nСканирование сети...");
            System.out.println("Диапазон: " + startIp + " - " + endIp);
            System.out.println();

            ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

            for (long ip = start; ip <= end; ip++) {
                String currentIp = longToIp(ip);

                int finalTimeout = timeout;
                executorService.submit(() -> {
                    if (isHostActive(currentIp, finalTimeout)) {
                        System.out.println("Активный компьютер найден: " + currentIp);
                    }
                });
            }

            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.MINUTES);

            System.out.println("\nСканирование завершено.");

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка IP-адреса: " + e.getMessage());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Сканирование было прервано.");
        }

        scanner.close();
    }

    private static boolean isHostActive(String ip, int timeout) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            return address.isReachable(timeout);
        } catch (IOException e) {
            return false;
        }
    }

    private static long ipToLong(String ipAddress) {
        String[] parts = ipAddress.split("\\.");

        if (parts.length != 4) {
            throw new IllegalArgumentException("Некорректный формат IP: " + ipAddress);
        }

        long result = 0;

        for (String part : parts) {
            int value;

            try {
                value = Integer.parseInt(part);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("IP содержит не число: " + ipAddress);
            }

            if (value < 0 || value > 255) {
                throw new IllegalArgumentException("Часть IP вне диапазона 0-255: " + ipAddress);
            }

            result = result * 256 + value;
        }

        return result;
    }

    private static String longToIp(long value) {
        return ((value >> 24) & 255) + "."
                + ((value >> 16) & 255) + "."
                + ((value >> 8) & 255) + "."
                + (value & 255);
    }
}
