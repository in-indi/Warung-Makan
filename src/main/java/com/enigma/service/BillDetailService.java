package com.enigma.service;

import com.enigma.model.BillDetail;

import java.util.List;

public interface BillDetailService {

    BillDetail create(BillDetail billDetail);

    List<BillDetail> getAll();
}
