package com.kaab.crud_app.controller.addresscontroller;

import com.kaab.crud_app.entity.address.Address;
import com.kaab.crud_app.entity.user.User;
import com.kaab.crud_app.service.addressservice.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/address")
public class addresscontroller {
    @Autowired
    private AddressService addressService;

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Address> createSingleAddress(@RequestBody Address address){
        Address createdAddress = addressService.createSingleAddress(address);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Address>> findAllAddress(){
        return new ResponseEntity<>(addressService.findAllAddress(),HttpStatus.OK);
    }
}
