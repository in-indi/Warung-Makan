package com.enigma.service;

import com.enigma.exception.NotFoundException;
import com.enigma.model.Bill;
import com.enigma.model.Customer;
import com.enigma.model.MenuPrice;
import com.enigma.repository.BillRepository;
import com.enigma.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;

    private CustomerRepository customerRepository;

    public BillServiceImpl(
            @Autowired BillRepository billRepository,
            @Autowired CustomerRepository customerRepository) {
        this.billRepository = billRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public Bill create(Bill bill) {
        try{
            Optional<Customer> customer = customerRepository.findById(bill.getCustomer().getId());
            if(customer.isEmpty()) {
                throw new NotFoundException("No Customer Found");
            }

            bill.setCustomer(customer.get());

            Bill result = billRepository.save(bill);

            return result;

        } catch (Exception e){
            throw new RuntimeException("Could not create Bill");
        }
    }

    @Transactional
    @Override
    public List<Bill> getAll() {
        List<Bill> bills = billRepository.findAll();
        if( bills.isEmpty()){
            throw new NotFoundException("No Bills Found");
        }
        return bills;
    }
}
