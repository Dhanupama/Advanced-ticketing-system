package com.ticket.sellingAndBuy.dto;

public class VendorDTO
{
    private int vendorId;
    private String vendorName;
    private String vendorEmail;
    private String ticketPrice;
    private int totalTickets;

    public VendorDTO()
    {
    }

    public VendorDTO( int vendorId, String vendorName, String vendorEmail, String ticketPrice, int totalTickets )
    {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.ticketPrice = ticketPrice;
        this.totalTickets = totalTickets;
    }

    public int getVendorId()
    {
        return vendorId;
    }

    public void setVendorId( int vendorId )
    {
        this.vendorId = vendorId;
    }

    public String getVendorName()
    {
        return vendorName;
    }

    public void setVendorName( String vendorName )
    {
        this.vendorName = vendorName;
    }

    public String getVendorEmail()
    {
        return vendorEmail;
    }

    public void setVendorEmail( String vendorEmail )
    {
        this.vendorEmail = vendorEmail;
    }

    public String getTicketPrice()
    {
        return ticketPrice;
    }

    public void setTicketPrice( String ticketPrice )
    {
        this.ticketPrice = ticketPrice;
    }

    public int getTotalTickets()
    {
        return totalTickets;
    }

    public void setTotalTickets( int totalTickets )
    {
        this.totalTickets = totalTickets;
    }
}