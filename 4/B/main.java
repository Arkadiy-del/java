package by.epam.appliances;

import by.epam.appliances.entity.ElectricalAppliance;
import by.epam.appliances.service.Apartment;
import by.epam.appliances.service.ApplianceInitializer;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DEFAULT_DATA_FILE = "src/main/resources/appliances.txt";

    public static void main(String[] args) {
        Apartment apartment = initApartment();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            choice = readInt(scanner);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    apartment.printAppliances();
                    break;
                case 2:
                    System.out.print("Введите название прибора: ");
                    String plugInName = scanner.nextLine();
                    if (apartment.plugInAppliance(plugInName)) {
                        System.out.println("Прибор включен в розетку.");
                    } else {
                        System.out.println("Прибор не найден.");
                    }
                    break;
                case 3:
                    System.out.print("Введите название прибора: ");
                    String unplugName = scanner.nextLine();
                    if (apartment.unplugAppliance(unplugName)) {
                        System.out.println("Прибор выключен из розетки.");
                    } else {
                        System.out.println("Прибор не найден.");
                    }
                    break;
                case 4:
                    System.out.println("Потребляемая мощность включенных приборов: "
                            + apartment.calculateCurrentPower() + " Вт");
                    break;
                case 5:
                    apartment.sortByPower();
                    System.out.println("Приборы отсортированы по мощности:");
                    apartment.printAppliances();
                    break;
                case 6:
                    System.out.print("Введите минимальную мощность: ");
                    int minPower = readInt(scanner);

                    System.out.print("Введите максимальную мощность: ");
                    int maxPower = readInt(scanner);

                    List<ElectricalAppliance> foundAppliances = apartment.findByPowerRange(minPower, maxPower);

                    if (foundAppliances.isEmpty()) {
                        System.out.println("Приборы не найдены.");
                    } else {
                        System.out.println("Найденные приборы:");
                        for (ElectricalAppliance appliance : foundAppliances) {
                            System.out.println(appliance);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Завершение программы.");
                    break;
                default:
                    System.out.println("Неверный пункт меню.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static Apartment initApartment() {
        try {
            Apartment apartment = ApplianceInitializer.loadFromFile(DEFAULT_DATA_FILE);
            apartment.plugInAppliance("Холодильник Samsung");
            apartment.plugInAppliance("Телевизор LG");
            return apartment;
        } catch (IOException | IllegalArgumentException exception) {
            System.out.println("Не удалось загрузить данные из файла. Будут использованы данные по умолчанию.");
            return ApplianceInitializer.createDefaultApartment();
        }
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Введите целое число.");
            scanner.next();
        }

        return scanner.nextInt();
    }

    private static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Показать все приборы");
        System.out.println("2. Включить прибор в розетку");
        System.out.println("3. Выключить прибор из розетки");
        System.out.println("4. Подсчитать потребляемую мощность");
        System.out.println("5. Отсортировать приборы по мощности");
        System.out.println("6. Найти приборы по диапазону мощности");
        System.out.println("0. Выход");
        System.out.print("Выберите пункт меню: ");
    }
}
