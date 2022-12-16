package com.enigma.service;

import com.enigma.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer create(Customer customer);

    List<Customer> getAll();

    Customer get(Long id);

    void delete(Long id);

    void update(Customer customer, Long id);


}
