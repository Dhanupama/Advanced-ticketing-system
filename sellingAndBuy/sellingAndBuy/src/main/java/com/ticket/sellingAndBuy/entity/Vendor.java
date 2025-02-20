package com.ticket.sellingAndBuy.entity;

import com.ticket.sellingAndBuy.service.TicketPoolService;
import jakarta.persistence.*;

@Entity
@Table(name = "vendor")
public class Vendor implements Runnable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vendorId;
    private String vendorName;
    private String vendorEmail;
    private String ticketPrice;
    private int totalTickets;

    @Transient
    private volatile boolean running = true;
    @Transient
    private TicketPoolService ticketPool;

    public Vendor() {}

    public Vendor(int vendorId, String vendorName, String vendorEmail, String ticketPrice, int totalTickets, TicketPoolService ticketPool) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.ticketPrice = ticketPrice;
        this.totalTickets = totalTickets;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        int ticketId = 0;
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
//                String ticket = "TicketId: " + ++ticketId + "_VendorId: " + vendorId;
                String ticket = "TicketId:" + ticketPool.getCurrentTicketId() + "_VendorId:" + vendorId;
                ticketPool.addTicket(ticket);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}