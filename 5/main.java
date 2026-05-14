package org.example;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(123456789, "Иван Иванов", 10000);

        account.deposit(5000);
        account.withdraw(2000);
        account.makePayment(1500, "Оплата интернета");
        account.makePayment(3000, "Оплата коммунальных услуг");

        System.out.println(account);
        System.out.println();

        account.printOperations();
    }
}
