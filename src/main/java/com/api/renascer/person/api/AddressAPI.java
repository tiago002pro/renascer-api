package com.api.renascer.person.api;

import com.api.renascer.person.service.AddressService;
import com.api.renascer.person.model.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressAPI {
    private final AddressService service;

    public AddressAPI(AddressService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Address address) {
        return ResponseEntity.ok((service).save(address));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Address address,
                                 @PathVariable Long id) {
        return ResponseEntity.ok((service).save(address));
    }
}
