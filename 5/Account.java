package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private long accountNumber;
    private String owner;
    private double balance;
    private List<Operation> operations;

    public Account() {
        operations = new ArrayList<>();
    }

    public Account(long accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.operations = new ArrayList<>();

        operations.add(new Operation(
                OperationType.INCOME,
                balance,
                "Начальный баланс"
        ));
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Сумма поступления должна быть больше 0.");
            return;
        }

        balance += amount;

        operations.add(new Operation(
                OperationType.INCOME,
                amount,
                "Поступление на счет"
        ));
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Сумма снятия должна быть больше 0.");
            return;
        }

        if (amount > balance) {
            System.out.println("Недостаточно средств на счете.");
            return;
        }

        balance -= amount;

        operations.add(new Operation(
                OperationType.WITHDRAWAL,
                amount,
                "Снятие со счета"
        ));
    }

    public void makePayment(double amount, String paymentPurpose) {
        if (amount <= 0) {
            System.out.println("Сумма платежа должна быть больше 0.");
            return;
        }

        if (amount > balance) {
            System.out.println("Недостаточно средств для платежа.");
            return;
        }

        balance -= amount;

        operations.add(new Operation(
                OperationType.PAYMENT,
                amount,
                paymentPurpose
        ));
    }

    public void printOperations() {
        if (operations.isEmpty()) {
            System.out.println("Операций по счету нет.");
            return;
        }

        System.out.println("История операций по счету №" + accountNumber + ":");

        for (Operation operation : operations) {
            System.out.println(operation);
        }
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "номер счета=" + accountNumber +
                ", владелец='" + owner + '\'' +
                ", баланс=" + balance +
                '}';
    }

    public enum OperationType {
        INCOME,
        WITHDRAWAL,
        PAYMENT
    }

    public class Operation {
        private OperationType type;
        private double amount;
        private String description;
        private LocalDateTime dateTime;
        private double balanceAfterOperation;

        public Operation(OperationType type, double amount, String description) {
            this.type = type;
            this.amount = amount;
            this.description = description;
            this.dateTime = LocalDateTime.now();
            this.balanceAfterOperation = Account.this.balance;
        }

        public OperationType getType() {
            return type;
        }

        public double getAmount() {
            return amount;
        }

        public String getDescription() {
            return description;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public double getBalanceAfterOperation() {
            return balanceAfterOperation;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

            return "Операция{" +
                    "тип=" + type +
                    ", сумма=" + amount +
                    ", описание='" + description + '\'' +
                    ", дата=" + dateTime.format(formatter) +
                    ", баланс после операции=" + balanceAfterOperation +
                    '}';
        }
    }
}
