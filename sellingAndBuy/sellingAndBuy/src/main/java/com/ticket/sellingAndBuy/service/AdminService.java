package com.ticket.sellingAndBuy.service;

import com.ticket.sellingAndBuy.dto.AdminDTO;
import com.ticket.sellingAndBuy.entity.Admin;
import com.ticket.sellingAndBuy.repo.AdminRepo;
import com.ticket.sellingAndBuy.utill.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class AdminService {

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private ModelMapper modelMapper;


    public String createadmin(AdminDTO adminDTO) {
        Admin admin = modelMapper.map(adminDTO, Admin.class);

        // Save the Admin entity to the repository
        adminRepo.save(admin);
        return VarList.RSP_SUCCESS;

    }
}
