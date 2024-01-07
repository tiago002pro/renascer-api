package com.api.renascer.person.service;

import com.api.renascer.person.repository.AddressRepository;
import com.api.renascer.person.model.Address;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Address save(Address address) {
        return repository.save(address);
    }
}
