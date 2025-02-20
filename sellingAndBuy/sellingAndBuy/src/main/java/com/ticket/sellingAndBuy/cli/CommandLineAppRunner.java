package com.ticket.sellingAndBuy.cli;

import com.ticket.sellingAndBuy.entity.Admin;
import com.ticket.sellingAndBuy.entity.Customer;
import com.ticket.sellingAndBuy.entity.Vendor;
import com.ticket.sellingAndBuy.service.ThreadManager;
import com.ticket.sellingAndBuy.service.TicketPoolService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineAppRunner implements CommandLineRunner {

    private final ThreadManager threadManager;
    private TicketPoolService ticketPoolService;
    private Admin admin;
    private List<Vendor> vendors;
    private List<Customer> customers;

    public CommandLineAppRunner(ThreadManager threadManager) {
        this.threadManager = threadManager;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        admin = initializeAdmin(scanner);
        ticketPoolService = new TicketPoolService(admin);

        vendors = new ArrayList<>();
        customers = new ArrayList<>();

        populateCustomersAndVendors( scanner );

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            handleMenuChoice(choice);
        }
    }

    private void populateCustomersAndVendors( Scanner scanner )
    {
        System.out.println("Enter number of vendors:");
        int vendorCount = scanner.nextInt();
        for (int i = 1; i <= vendorCount; i++) {
            vendors.add(new Vendor(i, "vendor" + i, "vendor" + i + "@example.com", "50.00", 20, ticketPoolService));
        }

        System.out.println("Enter number of customers:");
        int customerCount = scanner.nextInt();
        for (int i = 1; i <= customerCount; i++) {
            customers.add(new Customer(i, "customer" + i, "customer" + i + "@example.com", ticketPoolService));
        }
    }

    private Admin initializeAdmin(Scanner scanner) {
        int totalTicket = 0;
        int ticketRetrievalTime = 0;
        int customerRetrievalTime = 0;
        int maxTicketCapacity = 0;

        // Validate total tickets using while loop
        while (true) {
            System.out.println("Enter total tickets (positive integer):");
            if (scanner.hasNextInt()) {
                totalTicket = scanner.nextInt();
                if (totalTicket > 0) break;
                System.out.println("Invalid input. Total tickets must be a positive integer.");
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Validate ticket retrieval time using while loop
        while (true) {
            System.out.println("Enter ticket retrieval time (ms, positive integer):");
            if (scanner.hasNextInt()) {
                ticketRetrievalTime = scanner.nextInt();
                if (ticketRetrievalTime > 0) break;
                System.out.println("Invalid input. Retrieval time must be a positive integer.");
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Validate customer retrieval time using while loop
        while (true) {
            System.out.println("Enter customer retrieval time (ms, positive integer):");
            if (scanner.hasNextInt()) {
                customerRetrievalTime = scanner.nextInt();
                if (customerRetrievalTime > 0) break;
                System.out.println("Invalid input. Retrieval time must be a positive integer.");
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }


        while (true) {
            System.out.println("Enter max ticket capacity (positive integer):");
            if (scanner.hasNextInt()) {
                maxTicketCapacity = scanner.nextInt();
                if (maxTicketCapacity > 0) break;
                System.out.println("Invalid input. Max capacity must be a positive integer.");
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }

        return new Admin(totalTicket, ticketRetrievalTime, customerRetrievalTime, maxTicketCapacity);
    }


    private void displayMenu() {
        System.out.println("*************************************");
        System.out.println("Menu:");
        System.out.println("1. Start");
        System.out.println("2. Stop");
        System.out.println("3. Reset");
        System.out.println("4. Exit");
        System.out.println("*************************************");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                startThreads();
                break;
            case 2:
                stopThreads();
                break;
            case 3:
                resetThreads();
                break;
            case 4:
                exitApplication();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void startThreads() {
        if( threadManager.isRunning() )
        {
            System.out.println("Threads are already running.");
            return;
        }
        if( vendors.isEmpty() || customers.isEmpty() )
        {
            populateCustomersAndVendors( new Scanner( System.in ) );
        }
        for (Vendor vendor : vendors) {
            threadManager.start(vendor);
        }
        for (Customer customer : customers) {
            threadManager.start(customer);
        }
    }

    private void stopThreads() {
        if( !threadManager.isRunning() )
        {
            System.out.println("Threads are already stopped.");
            return;
        }
        for (Vendor vendor : vendors) {
            threadManager.stop(vendor);
        }
        for (Customer customer : customers) {
            threadManager.stop(customer);
        }
    }

    private void resetThreads() {
        stopThreads();
        ticketPoolService = new TicketPoolService(admin);
        vendors.clear();
        customers.clear();
    }

    private void exitApplication() {
        stopThreads();
        System.out.println("Exiting...");
        System.exit(0);
    }
}