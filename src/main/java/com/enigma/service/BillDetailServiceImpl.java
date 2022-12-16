package com.enigma.service;

import com.enigma.exception.NotFoundException;
import com.enigma.model.Bill;
import com.enigma.model.BillDetail;
import com.enigma.model.Member;
import com.enigma.model.MenuPrice;
import com.enigma.repository.BillDetailRepository;
import com.enigma.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class BillDetailServiceImpl implements BillDetailService{

    private BillDetailRepository detailRepository;

    private BillRepository billRepository;

    public BillDetailServiceImpl(
            @Autowired BillDetailRepository detailRepository,
            @Autowired BillRepository billRepository) {
        this.detailRepository = detailRepository;
        this.billRepository = billRepository;
    }


    @Override
    public BillDetail create(BillDetail billDetail) {
        try{
            Optional<Bill> bill = billRepository.findById(billDetail.getBill().getId());
            if(bill.isEmpty()) {
                System.out.println("Non Member Purchase");
            }
            return detailRepository.save(billDetail);
        } catch (Exception e){
            throw new RuntimeException("Can't create customer");
        }
    }

    @Override
    public List<BillDetail> getAll() {
        List<BillDetail> details = detailRepository.findAll();
        if(details.isEmpty()){
            throw new NotFoundException("No Billing Detail exist");
        }
        return details;
    }
}
