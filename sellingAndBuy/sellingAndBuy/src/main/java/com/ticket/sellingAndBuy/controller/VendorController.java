package com.ticket.sellingAndBuy.controller;

import com.ticket.sellingAndBuy.dto.ResponseDTO;
import com.ticket.sellingAndBuy.dto.VendorDTO;
import com.ticket.sellingAndBuy.repo.VendorRepo;
import com.ticket.sellingAndBuy.service.VendorService;
import com.ticket.sellingAndBuy.utill.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping( "api/v1/vendor" )
public class VendorController
{

    private final VendorService vendorService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VendorRepo vendorRepo;
    @Autowired
    private VendorService vendorService1;
    @Autowired
    private ResponseDTO responseDTO;
    public VendorController( VendorService vendorService )
    {
        this.vendorService = vendorService;
    }

    @PostMapping( value = "/saveVendor" )
    public ResponseEntity saveVendor( @RequestBody VendorDTO vendorDTO )
    {
        try
        {
            String res = vendorService.saveVendor( vendorDTO );
            if( res.equals( "00" ) )
            {
                responseDTO.setCode( VarList.RSP_SUCCESS );
                responseDTO.setMessage( "Success" );
                responseDTO.setContent( vendorDTO );
                return new ResponseEntity( responseDTO, HttpStatus.ACCEPTED );


            }
            else if( res.equals( "06" ) )
            {
                responseDTO.setCode( VarList.RSP_DUPLICATED );
                responseDTO.setMessage( "Vendor recoded" );
                responseDTO.setContent( vendorDTO );
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

    @PutMapping( value = "/updateVendor" )
    public ResponseEntity updateVendor( @RequestBody VendorDTO vendorDTO )
    {
        try
        {
            String res = vendorService.saveVendor( vendorDTO );
            if( res.equals( "00" ) )
            {
                responseDTO.setCode( VarList.RSP_SUCCESS );
                responseDTO.setMessage( "Success" );
                responseDTO.setContent( vendorDTO );
                return new ResponseEntity( responseDTO, HttpStatus.ACCEPTED );


            }
            else if( res.equals( "01" ) )
            {
                responseDTO.setCode( VarList.RSP_DUPLICATED );
                responseDTO.setMessage( "Not A Recoded vendor" );
                responseDTO.setContent( vendorDTO );
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

    @GetMapping( value = "/getAllVendors" )
    public ResponseEntity getAllVendors()
    {
        try
        {
            List<VendorDTO> vendorDTOList = vendorService.getAllVendors();
            responseDTO.setCode( VarList.RSP_SUCCESS );
            responseDTO.setMessage( "Success" );
            responseDTO.setContent( vendorDTOList );
            return new ResponseEntity( responseDTO, HttpStatus.ACCEPTED );


        }
        catch( Exception ex )
        {
            responseDTO.setCode( VarList.RSP_ERROR );
            responseDTO.setMessage( "Error" );
            responseDTO.setContent( null );
            return new ResponseEntity( responseDTO, HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }

    @DeleteMapping( "/deleteVendor/{vendorId}" )
    public ResponseEntity deleteVendor( @PathVariable int vendorId )
    {

        try
        {
            String tempResponse = vendorService.deleteVendor( vendorId );
            if( tempResponse.equals( "00" ) )
            {
                responseDTO.setCode( VarList.RSP_SUCCESS );
                responseDTO.setMessage( "Success" );
                responseDTO.setContent( null );
                return new ResponseEntity( responseDTO, HttpStatus.ACCEPTED );

            }
            else
            {
                responseDTO.setCode( VarList.RSP_NO_DATA_FOUND );
                responseDTO.setMessage( " Not Found Vendor for this ID" );
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

    public ResponseEntity searchVendor( @PathVariable int vendorId )
    {

        try
        {
            VendorDTO vendorDTO = vendorService.searchVendor( vendorId );
            if( vendorDTO != null )
            {
                responseDTO.setCode( VarList.RSP_SUCCESS );
                responseDTO.setMessage( "Success" );
                responseDTO.setContent( vendorDTO );
                return new ResponseEntity( responseDTO, HttpStatus.ACCEPTED );

            }
            else
            {
                responseDTO.setCode( VarList.RSP_ERROR );
                responseDTO.setMessage( " Not Found Vendor for this ID" );
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
