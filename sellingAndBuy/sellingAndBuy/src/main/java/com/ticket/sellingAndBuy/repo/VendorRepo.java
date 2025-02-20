package com.ticket.sellingAndBuy.repo;

import com.ticket.sellingAndBuy.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepo extends JpaRepository<Vendor,Integer>
{
}
