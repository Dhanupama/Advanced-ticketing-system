package com.ticket.sellingAndBuy.service;

import com.ticket.sellingAndBuy.dto.CustomerDTO;
import com.ticket.sellingAndBuy.entity.Customer;
import com.ticket.sellingAndBuy.repo.CustomerRepo;
import com.ticket.sellingAndBuy.utill.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService
{

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveCustomer( CustomerDTO customerDTO )
    {
        if( customerRepo.existsById( customerDTO.getTicketId() ) )
        {
            return VarList.RSP_DUPLICATED;
        }
        else
        {
            customerRepo.save( modelMapper.map( customerDTO, Customer.class ) );
            return VarList.RSP_SUCCESS;
        }

    }

}
