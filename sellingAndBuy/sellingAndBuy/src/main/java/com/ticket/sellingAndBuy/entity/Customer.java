package com.ticket.sellingAndBuy.entity;

import com.ticket.sellingAndBuy.service.TicketPoolService;
import jakarta.persistence.*;

@Entity
@Table(name = "Customer")

public class Customer implements Runnable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;
    private String customerName;
    private String customerEmail;

    @Transient
    private volatile boolean running = true;
    @Transient
    private TicketPoolService ticketPool;

    public Customer() {}

    public Customer(int ticketId, String customerName, String customerEmail, TicketPoolService ticketPool) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                ticketPool.removeTicket();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}