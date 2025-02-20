package com.ticket.sellingAndBuy.controller;

import com.ticket.sellingAndBuy.dto.CustomerDTO;
import com.ticket.sellingAndBuy.dto.ResponseDTO;
import com.ticket.sellingAndBuy.repo.CustomerRepo;
import com.ticket.sellingAndBuy.service.CustomerService;
import com.ticket.sellingAndBuy.utill.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "api/v2/customer" )
public class CustomerController
{
    private final CustomerService customerService;
    @Autowired
    private ModelMapper modelMapper;

    private CustomerRepo customerRepo;

    private CustomerDTO customerDTO1;
    private ResponseDTO responseDTO;

    public CustomerController( CustomerService customerService )
    {
        this.customerService = customerService;
    }

    @PostMapping( value = "/saveCustomer" )
    public ResponseEntity saveCustomer( @RequestBody CustomerDTO customerDTO )
    {
        try
        {
            String res = this.customerService.saveCustomer( customerDTO );
            if( res.equals( "00" ) )
            {
                responseDTO.setCode( VarList.RSP_SUCCESS );
                responseDTO.setMessage( "Success" );
                responseDTO.setContent( customerDTO );
                return new ResponseEntity( responseDTO, HttpStatus.ACCEPTED );


            }
            else if( res.equals( "06" ) )
            {
                responseDTO.setCode( VarList.RSP_DUPLICATED );
                responseDTO.setMessage( "Vendor recoded" );
                responseDTO.setContent( customerDTO );
                return new ResponseEntity( responseDTO, HttpStatus.BAD_REQUEST );

            }
            else
            {
                responseDTO.setCode( VarList.RSP_ERROR );
                responseDTO.setMessage( "Error" );
                responseDTO.setContent( null );
                return new ResponseEntity( responseDTO, HttpStatus.BAD_REQUEST );

            }

        }
        catch( Exception ex )
        {
            responseDTO.setCode( VarList.RSP_ERROR );
            responseDTO.setMessage( "Error" );
            responseDTO.setContent( null );
            return new ResponseEntity( responseDTO, HttpStatus.INTERNAL_SERVER_ERROR );


        }
    }

}
