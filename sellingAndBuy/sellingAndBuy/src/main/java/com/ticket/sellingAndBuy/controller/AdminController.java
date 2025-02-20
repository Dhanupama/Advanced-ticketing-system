package com.ticket.sellingAndBuy.controller;

import com.ticket.sellingAndBuy.dto.AdminDTO;
import com.ticket.sellingAndBuy.dto.ResponseDTO;
import com.ticket.sellingAndBuy.repo.AdminRepo;
import com.ticket.sellingAndBuy.service.AdminService;
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
@RequestMapping("api/v3/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AdminRepo adminRepo;
    private ResponseDTO responseDTO;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/createadmin")
    public ResponseEntity createadmin(@RequestBody AdminDTO adminDTO) {
        try {
            String res = this.adminService.createadmin(adminDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);


            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Vendor recoded");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            } else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);


        }

    }
}
