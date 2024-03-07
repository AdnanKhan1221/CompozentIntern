package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Car {
    private String make;
    private String model;
    private int year;
    private boolean available;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.available = true;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        available = false;
    }

    public void returnCar() {
        available = true;
    }
}

class RentalRecord {
    private String customerName;
    private String rentedCar;

    public RentalRecord(String customerName, String rentedCar) {
        this.customerName = customerName;
        this.rentedCar = rentedCar;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRentedCar() {
        return rentedCar;
    }
}

public class CarRentalSystem {
    private static HashMap<String, Car> cars = new HashMap<>();
    private static ArrayList<RentalRecord> rentalHistory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCars();

        while (true) {
            System.out.println("\nCar Rental System");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. View Rental History");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    rentCar();
                    break;
                case 2:
                    returnCar();
                    break;
                case 3:
                    viewRentalHistory();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeCars() {
        cars.put("ABC123", new Car("Toyota", "Camry", 2020));
        cars.put("DEF456", new Car("Honda", "Accord", 2019));
        cars.put("GHI789", new Car("Ford", "Mustang", 2021));
    }

    private static void rentCar() {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.println("\nAvailable Cars:");
        for (String plateNumber : cars.keySet()) {
            Car car = cars.get(plateNumber);
            if (car.isAvailable()) {
                System.out.println(plateNumber + " - " + car.getMake() + " " + car.getModel() + " (" + car.getYear() + ")");
            }
        }

        System.out.print("Enter the plate number of the car you want to rent: ");
        String plateNumber = scanner.nextLine();
        if (cars.containsKey(plateNumber) && cars.get(plateNumber).isAvailable()) {
            cars.get(plateNumber).rent();
            rentalHistory.add(new RentalRecord(customerName, plateNumber));
            System.out.println("Car rented successfully.");
        } else {
            System.out.println("Invalid plate number or car is not available for rent.");
        }
    }

    private static void returnCar() {
        System.out.print("Enter the plate number of the car you are returning: ");
        String plateNumber = scanner.nextLine();
        if (cars.containsKey(plateNumber) && !cars.get(plateNumber).isAvailable()) {
            cars.get(plateNumber).returnCar();
            for (RentalRecord record : rentalHistory) {
                if (record.getRentedCar().equals(plateNumber)) {
                    rentalHistory.remove(record);
                    System.out.println("Car returned successfully.");
                    return;
                }
            }
            System.out.println("Car not found in rental history.");
        } else {
            System.out.println("Invalid plate number or car is not currently rented.");
        }
    }

    private static void viewRentalHistory() {
        if (rentalHistory.isEmpty()) {
            System.out.println("Rental history is empty.");
        } else {
            System.out.println("Rental History:");
            for (RentalRecord record : rentalHistory) {
                System.out.println("Customer: " + record.getCustomerName() + ", Car: " + record.getRentedCar());
            }
        }
    }
}