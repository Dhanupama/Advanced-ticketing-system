package com.ticket.sellingAndBuy.repo;

import com.ticket.sellingAndBuy.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer>
{
}
