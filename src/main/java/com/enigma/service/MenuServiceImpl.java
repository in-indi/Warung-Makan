package com.enigma.service;

import com.enigma.exception.NotFoundException;
import com.enigma.model.Customer;
import com.enigma.model.Member;
import com.enigma.model.Menu;
import com.enigma.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository  menuRepository;

    private ModelMapper modelMapper;

    public MenuServiceImpl(
            @Autowired MenuRepository menuRepository,
            @Autowired ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Menu create(Menu menu) {
        try {
            return menuRepository.save(menu);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("Could not create Menu");
        }
    }

    @Transactional
    public List<Menu> getAll() {
        List<Menu> menus = menuRepository.findAll();
        if( menus.isEmpty()){
            throw new NotFoundException("No Menu Found");
        }
        return menus;
    }

    @Override
    public Menu get(Long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if(menu.isEmpty()) {
            throw new NotFoundException("Course " + id + " not found");
        }
        return menu.get();    }

    @Override
    public void update(Menu menu, Long id) {
        try {
            Menu existingMenu = get(id);
            modelMapper.map(menu, existingMenu);
            menuRepository.save(existingMenu);
        } catch(NotFoundException e) {
            throw new NotFoundException("Update failed, ID not found");
        }
    }
}
