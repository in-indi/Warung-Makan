package com.enigma.model.request;

import com.enigma.model.Bill;
import com.enigma.model.MenuPrice;
import lombok.Data;

@Data
public class BillDetailRequest {

    private MenuPrice menuPrice;

    private Bill bill;


}
