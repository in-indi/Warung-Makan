package com.enigma.service;

import com.enigma.model.Bill;

import java.util.List;

public interface BillService {

    Bill create(Bill bill);

    List<Bill> getAll();

}
