package com.enigma.model.request;

import com.enigma.model.BillDetail;
import com.enigma.model.Menu;
import lombok.Data;

import java.util.List;

@Data
public class MenuPriceRequest {

    private String name;

    private Menu menu;

}
