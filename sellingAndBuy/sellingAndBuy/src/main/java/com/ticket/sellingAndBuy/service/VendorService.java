package com.ticket.sellingAndBuy.service;

import com.ticket.sellingAndBuy.dto.VendorDTO;
import com.ticket.sellingAndBuy.entity.Vendor;
import com.ticket.sellingAndBuy.repo.VendorRepo;
import com.ticket.sellingAndBuy.utill.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VendorService
{

    @Autowired
    private VendorRepo vendorRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveVendor( VendorDTO vendorDTO )
    {
        if( vendorRepo.existsById( vendorDTO.getVendorId() ) )
        {
            return VarList.RSP_DUPLICATED;
        }
        else
        {
            vendorRepo.save( modelMapper.map( vendorDTO, Vendor.class ) );
            return VarList.RSP_SUCCESS;
        }

    }

    public String updateVendor( VendorDTO vendorDTO )
    {
        if( vendorRepo.existsById( vendorDTO.getVendorId() ) )
        {
            vendorRepo.save( modelMapper.map( vendorDTO, Vendor.class ) );
            return VarList.RSP_SUCCESS;
        }
        else
        {
            return VarList.RSP_NO_DATA_FOUND;

        }
    }

    public List<VendorDTO> getAllVendors()
    {
        List<Vendor> vendorList = vendorRepo.findAll();
        return modelMapper.map( vendorList, new TypeToken<List<VendorDTO>>()
        {

        }.getType() );
    }

    public VendorDTO searchVendor( int vendorId )
    {
        if( vendorRepo.existsById( vendorId ) )
        {
            Vendor vendor = vendorRepo.findById( vendorId ).orElse( null );
            return modelMapper.map( vendor, VendorDTO.class );

        }
        else
        {
            return null;
        }
    }

    public String deleteVendor( int vendorId )
    {
        if( vendorRepo.existsById( vendorId ) )
        {
            vendorRepo.deleteById( vendorId );
            return VarList.RSP_SUCCESS;

        }
        else
        {
            return VarList.RSP_NO_DATA_FOUND;
        }


    }
}
