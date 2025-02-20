package com.ticket.sellingAndBuy.repo;

import com.ticket.sellingAndBuy.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Integer> {


}
