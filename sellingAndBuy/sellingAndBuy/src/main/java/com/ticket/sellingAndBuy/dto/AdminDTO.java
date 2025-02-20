package com.ticket.sellingAndBuy.dto;

public class AdminDTO {

    private int totalTicket;
    private int ticketRetrivalTime;
    private int customerRetrivalTime;
    private int maxTicketCapacity;

    public AdminDTO() {
    }

    public AdminDTO(int totalTicket, int ticketRetrivalTime, int customerRetrivalTime, int maxTicketCapacity) {
        this.totalTicket = totalTicket;
        this.ticketRetrivalTime = ticketRetrivalTime;
        this.customerRetrivalTime = customerRetrivalTime;
        this.maxTicketCapacity = maxTicketCapacity;
    }

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
}
