package com.enigma.service;

import com.enigma.model.Customer;
import com.enigma.model.MenuPrice;

import java.util.List;

public interface MenuPriceService {

    MenuPrice create(MenuPrice menuPrice);

    List<MenuPrice> getAll();

    MenuPrice get(Long id);

    void delete(Long id);

    void update(MenuPrice price, Long id);

}
