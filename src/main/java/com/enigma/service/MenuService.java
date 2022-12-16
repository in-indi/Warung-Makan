package com.enigma.service;

import com.enigma.model.Member;
import com.enigma.model.Menu;

import java.util.List;

public interface MenuService {

    Menu create(Menu menu);

    List<Menu> getAll();

    Menu get(Long id);

    void update(Menu menu, Long id);
}
