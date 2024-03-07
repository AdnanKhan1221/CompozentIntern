package com;

import java.util.HashMap;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    public boolean transfer(Account receiver, double amount) {
        if (withdraw(amount)) {
            receiver.deposit(amount);
            return true;
        } else {
            return false;
        }
    }
}

public class ATMSimulator {
    private static HashMap<String, Account> accounts = new HashMap<>();
    private static Account currentAccount;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create some sample accounts
        accounts.put("123456", new Account("123456", "1234", 1000.0));
        accounts.put("654321", new Account("654321", "4321", 500.0));

        // ATM operation loop
        while (true) {
            System.out.println("\nATM Simulator");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (accounts.containsKey(accountNumber) && accounts.get(accountNumber).getPin().equals(pin)) {
            currentAccount = accounts.get(accountNumber);
            showMainMenu();
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    currentAccount = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        currentAccount.deposit(amount);
        System.out.println("Deposit successful. Current balance: " + currentAccount.getBalance());
    }

    private static void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        if (currentAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful. Current balance: " + currentAccount.getBalance());
        }
    }

    private static void transfer() {
        System.out.print("Enter recipient account number: ");
        String recipientAccountNumber = scanner.nextLine();
        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        if (accounts.containsKey(recipientAccountNumber)) {
            Account receiver = accounts.get(recipientAccountNumber);
            if (currentAccount.transfer(receiver, amount)) {
                System.out.println("Transfer successful. Current balance: " + currentAccount.getBalance());
            }
        } else {
            System.out.println("Recipient account not found.");
        }
    }
}