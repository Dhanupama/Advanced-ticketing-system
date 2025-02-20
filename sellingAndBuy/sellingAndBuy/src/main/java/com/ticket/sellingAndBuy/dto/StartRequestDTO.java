package com.ticket.sellingAndBuy.dto;

public class StartRequestDTO {
    private int totalTicket;
    private int ticketRetrivalTime;
    private int customerRetrivalTime;
    private int maxTicketCapacity;
    private int customerCount;
    private int vendorCount;

    // Default Constructor
    public StartRequestDTO() {
    }

    // Parameterized Constructor
    public StartRequestDTO(int totalTicket, int ticketRetrivalTime, int customerRetrivalTime, int maxTicketCapacity, int customerCount, int vendorCount) {
        this.totalTicket = totalTicket;
        this.ticketRetrivalTime = ticketRetrivalTime;
        this.customerRetrivalTime = customerRetrivalTime;
        this.maxTicketCapacity = maxTicketCapacity;
        this.customerCount = customerCount;
        this.vendorCount = vendorCount;
    }

    // Getters and Setters
    public int getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(int totalTicket) {
        this.totalTicket = totalTicket;
    }

    public int getTicketRetrivalTime() {
        return ticketRetrivalTime;
    }

    public void setTicketRetrivalTime(int ticketRetrivalTime) {
        this.ticketRetrivalTime = ticketRetrivalTime;
    }

    public int getCustomerRetrivalTime() {
        return customerRetrivalTime;
    }

    public void setCustomerRetrivalTime(int customerRetrivalTime) {
        this.customerRetrivalTime = customerRetrivalTime;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }
}