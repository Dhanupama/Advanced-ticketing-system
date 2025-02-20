package com.ticket.sellingAndBuy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;
@Entity
@Component
public class Admin
{
    @Id
    private int totalTicket;
    private int ticketRetrivalTime;
    private int customerRetrivalTime;
    private int maxTicketCapacity;

    public Admin( int totalTicket, int ticketRetrivalTime, int customerRetrivalTime, int maxTicketCapacity )
    {
        this.totalTicket = totalTicket;
        this.ticketRetrivalTime = ticketRetrivalTime;
        this.customerRetrivalTime = customerRetrivalTime;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public Admin()
    {
    }

    public int getTotalTicket()
    {
        return totalTicket;
    }

    public void setTotalTicket( int totalTicket )
    {
        this.totalTicket = totalTicket;
    }

    public int getTicketRetrivalTime()
    {
        return ticketRetrivalTime;
    }

    public void setTicketRetrivalTime( int ticketRetrivalTime )
    {
        this.ticketRetrivalTime = ticketRetrivalTime;
    }

    public int getCustomerRetrivalTime()
    {
        return customerRetrivalTime;
    }

    public void setCustomerRetrivalTime( int customerRetrivalTime )
    {
        this.customerRetrivalTime = customerRetrivalTime;
    }

    public int getMaxTicketCapacity()
    {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity( int maxTicketCapacity )
    {
        this.maxTicketCapacity = maxTicketCapacity;
    }
}
