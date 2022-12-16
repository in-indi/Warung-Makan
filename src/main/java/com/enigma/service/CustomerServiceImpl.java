package com.enigma.service;

import com.enigma.exception.NotFoundException;
import com.enigma.model.Customer;
import com.enigma.model.Member;
import com.enigma.repository.CustomerRepository;
import com.enigma.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;

    private MemberRepository memberRepository;

    private ModelMapper modelMapper;

    public CustomerServiceImpl(
            @Autowired CustomerRepository customerRepository,
            @Autowired MemberRepository memberRepository,
            @Autowired ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Customer create(Customer customer) {
        try{
            Optional<Member> member = memberRepository.findById(customer.getMember().getId());
            if(member.isEmpty()) {
                System.out.println("Non Member Purchase");
            }
            return customerRepository.save(customer);
        } catch (Exception e){
            throw new RuntimeException("Can't create customer");
        }
    }

    @Transactional
    public List<Customer> getAll() {
        List<Customer> customers = customerRepository.findAll();
        if( customers.isEmpty()){
            throw new NotFoundException("No Products found");
        }
        return customers;
    }

    @Override
    public Customer get(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()) {
            throw new NotFoundException("Course " + id + " not found");
        }
        return customer.get();
    }

    @Override
    public void delete(Long id) {
        try {
            Customer existingCustomer = get(id);
            customerRepository.delete(existingCustomer);
        } catch(NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }

    @Override
    public void update(Customer customer, Long id) {
        try {
            Customer existingCustomer = get(id);
            modelMapper.map(customer, existingCustomer);
            customerRepository.save(existingCustomer);
        } catch(NotFoundException e) {
            throw new NotFoundException("Update failed, ID not found");
        }
    }
}
