package com.kaab.crud_app.service.addressservice;

import com.kaab.crud_app.entity.address.Address;
import com.kaab.crud_app.repository.addressrepo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    public Address createSingleAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }
}
