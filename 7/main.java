package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class User {
    private int id;
    private String name;
    private int age;
    private String country;

    public User(int id, String name, int age, String country) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", имя='" + name + '\'' +
                ", возраст=" + age +
                ", страна='" + country + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User[] users = {
                new User(1, "Алексей", 25, "Россия"),
                new User(2, "Анна", 32, "Беларусь"),
                new User(3, "Иван", 19, "Россия"),
                new User(4, "Артем", 40, "Казахстан"),
                new User(5, "Мария", 28, "Россия"),
                new User(6, "Алина", 35, "Беларусь"),
                new User(7, "Олег", 45, "Казахстан"),
                new User(8, "Андрей", 22, "Россия")
        };

        System.out.println("Исходный массив пользователей:");
        for (User user : users) {
            System.out.println(user);
        }

        Arrays.sort(users,
                Comparator.comparing(User::getCountry)
                        .thenComparing(User::getAge)
        );

        System.out.println("\nПользователи после сортировки по стране и возрасту:");
        for (User user : users) {
            System.out.println(user);
        }

        System.out.print("\nВведите возраст: ");
        int ageLimit = scanner.nextInt();

        System.out.print("Введите первую букву имени: ");
        char firstLetter = scanner.next().charAt(0);

        List<User> filteredUsers = Arrays.stream(users)
                .filter(user -> user.getAge() > ageLimit)
                .filter(user -> Character.toLowerCase(user.getName().charAt(0))
                        == Character.toLowerCase(firstLetter))
                .collect(Collectors.toList());

        System.out.println("\nПользователи старше " + ageLimit
                + ", имя которых начинается с буквы '" + firstLetter + "':");

        if (filteredUsers.isEmpty()) {
            System.out.println("Пользователи не найдены.");
        } else {
            filteredUsers.forEach(System.out::println);
        }

        Map<Integer, List<User>> groupedByAge = filteredUsers.stream()
                .collect(Collectors.groupingBy(User::getAge));

        System.out.println("\nСгруппированный результат по возрасту:");
        groupedByAge.forEach((age, userList) -> {
            System.out.println("Возраст: " + age);
            userList.forEach(System.out::println);
        });

        User minUser = filteredUsers.stream()
                .min(Comparator.comparing(User::getAge))
                .orElse(null);

        User maxUser = filteredUsers.stream()
                .max(Comparator.comparing(User::getAge))
                .orElse(null);

        System.out.println("\nМинимальный элемент по возрасту:");
        if (minUser != null) {
            System.out.println(minUser);
        } else {
            System.out.println("Нет данных.");
        }

        System.out.println("\nМаксимальный элемент по возрасту:");
        if (maxUser != null) {
            System.out.println(maxUser);
        } else {
            System.out.println("Нет данных.");
        }

        scanner.close();
    }
}
