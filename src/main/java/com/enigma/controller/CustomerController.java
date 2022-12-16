package com.enigma.controller;

import com.enigma.constants.UrlMappings;
import com.enigma.model.Customer;
import com.enigma.model.request.CustomerRequest;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.CustomerService;
import org.h2.engine.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMappings.CUSTOMER_URL)
public class CustomerController {

    private ModelMapper modelMapper;

    private CustomerService service;

    public CustomerController(@Autowired ModelMapper modelMapper, @Autowired CustomerService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody CustomerRequest request) throws Exception {
        Customer newCustomer = modelMapper.map(request, Customer.class);

        Customer result = service.create(newCustomer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>( "Success add customer",result));
    }

    @GetMapping
    public ResponseEntity getAllCustomer() throws Exception {

        List<Customer> result= service.getAll();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>( "Success get all customers",result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Customer customer = service.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find ID", customer));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody CustomerRequest request, @PathVariable("id") Long id) {
        Customer existingCustomer = modelMapper.map(request, Customer.class);
        existingCustomer.setId(id);
        service.update(existingCustomer, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully update", request));
    }


}
