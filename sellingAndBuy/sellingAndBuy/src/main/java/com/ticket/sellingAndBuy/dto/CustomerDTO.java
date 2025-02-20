package com.ticket.sellingAndBuy.dto;

public class CustomerDTO
{
    private int ticketId;
    private String customerName;
    private String customerEmail;

    public CustomerDTO()
    {
    }

    public CustomerDTO( int ticketId, String customerName, String customerEmail )
    {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public int getTicketId()
    {
        return ticketId;
    }

    public void setTicketId( int ticketId )
    {
        this.ticketId = ticketId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName( String customerName )
    {
        this.customerName = customerName;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public void setCustomerEmail( String customerEmail )
    {
        this.customerEmail = customerEmail;
    }
}
