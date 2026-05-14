package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Patient[] patients = {
                new Patient(1, "Иванов", "Иван", "Иванович",
                        "Москва", "+79990001122", 101, "Грипп"),

                new Patient(2, "Петров", "Петр", "Петрович",
                        "Казань", "+79990003344", 205, "Ангина"),

                new Patient(3, "Сидорова", "Анна", "Сергеевна",
                        "Санкт-Петербург", "+79990005566", 150, "Грипп"),

                new Patient(4, "Кузнецов", "Алексей", "Олегович",
                        "Новосибирск", "+79990007788", 310, "Бронхит"),

                new Patient(5, "Смирнова", "Мария", "Игоревна",
                        "Омск", "+79990009900", 180, "Ангина")
        };

        PatientArray patientArray = new PatientArray(patients);

        System.out.println("Список всех пациентов:");
        patientArray.printAllPatients();

        System.out.print("\nВведите диагноз для поиска: ");
        String diagnosis = scanner.nextLine();

        patientArray.printPatientsByDiagnosis(diagnosis);

        System.out.print("\nВведите начало интервала номера медицинской карты: ");
        int min = scanner.nextInt();

        System.out.print("Введите конец интервала номера медицинской карты: ");
        int max = scanner.nextInt();

        patientArray.printPatientsByMedicalCardInterval(min, max);

        scanner.close();
    }
}
