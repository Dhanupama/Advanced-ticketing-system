package com.ticket.sellingAndBuy.controller;

import com.ticket.sellingAndBuy.dto.StartRequestDTO;
import com.ticket.sellingAndBuy.entity.Admin;
import com.ticket.sellingAndBuy.entity.Customer;
import com.ticket.sellingAndBuy.entity.Vendor;
import com.ticket.sellingAndBuy.service.ThreadManager;
import com.ticket.sellingAndBuy.service.TicketPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {

    private final ThreadManager threadManager;
    private TicketPoolService ticketPoolService;
    private Admin admin;
    private List<Customer> customers;
    private List<Vendor> vendors;

    @Autowired
    public TicketController(ThreadManager threadManager) {
        this.threadManager = threadManager;
        this.customers = new ArrayList<>();
        this.vendors = new ArrayList<>();
    }

    // API to initialize the Admin and start the threads for vendors and customers
    @PostMapping("/start")
    public String startThreads(@RequestBody StartRequestDTO startRequest) {
        // Create Admin and TicketPoolService
        this.admin = new Admin(
                startRequest.getTotalTicket(),
                startRequest.getTicketRetrivalTime(),
                startRequest.getCustomerRetrivalTime(),
                startRequest.getMaxTicketCapacity()
        );

        this.ticketPoolService = new TicketPoolService(admin);

        //  Create Customers and Vendors based on the input
        populateCustomersAndVendors(startRequest);

        //  Start the threads for vendors and customers
        startThreads();

        return "Threads started successfully.";
    }

    // Populate customers and vendors based on the request
    private void populateCustomersAndVendors(StartRequestDTO startRequest) {
        vendors.clear();
        customers.clear();

        // Create vendors
        for (int i = 1; i <= startRequest.getVendorCount(); i++) {
            vendors.add(new Vendor(i, "vendor" + i, "vendor" + i + "@example.com", "50.00", 20, ticketPoolService));
        }

        // Create customers
        for (int i = 1; i <= startRequest.getCustomerCount(); i++) {
            customers.add(new Customer(i, "customer" + i, "customer" + i + "@example.com", ticketPoolService));
        }
    }

    // Start the threads for vendors and customers
    private void startThreads() {
        if (threadManager.isRunning()) {
            System.out.println("Threads are already running.");
            return;
        }

        // Start vendor threads
        for (Vendor vendor : vendors) {
            threadManager.start(vendor);
        }

        // Start customer threads
        for (Customer customer : customers) {
            threadManager.start(customer);
        }
    }
}