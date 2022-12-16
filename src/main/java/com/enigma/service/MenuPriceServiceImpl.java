package com.enigma.service;

import com.enigma.exception.NotFoundException;
import com.enigma.model.Customer;
import com.enigma.model.Menu;
import com.enigma.model.MenuPrice;
import com.enigma.repository.MenuPriceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MenuPriceServiceImpl implements MenuPriceService{

    private MenuPriceRepository priceRepository;

    private ModelMapper modelMapper;

    public MenuPriceServiceImpl(
            @Autowired MenuPriceRepository priceRepository,
            @Autowired ModelMapper modelMapper) {
        this.priceRepository = priceRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public MenuPrice create(MenuPrice menuPrice) {
        try{
            return priceRepository.save(menuPrice);
        } catch (DataIntegrityViolationException e){
            throw new EntityExistsException("Could not create menu price");
        }
    }

    @Transactional
    @Override
    public List<MenuPrice> getAll() {
        List<MenuPrice> prices = priceRepository.findAll();
        if( prices.isEmpty()){
            throw new NotFoundException("No Menu Prices Found");
        }
        return prices;
    }

    @Override
    public MenuPrice get(Long id) {
        Optional<MenuPrice> price = priceRepository.findById(id);
        if(price.isEmpty()) {
            throw new NotFoundException("Course " + id + " not found");
        }
        return price.get();
    }

    @Override
    public void delete(Long id) {
        try {
            MenuPrice existingPrice = get(id);
            priceRepository.delete(existingPrice);
        } catch(NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }

    @Override
    public void update(MenuPrice price, Long id) {
        try {
            MenuPrice existingPrice = get(id);
            modelMapper.map(price, existingPrice);
            priceRepository.save(existingPrice);
        } catch(NotFoundException e) {
            throw new NotFoundException("Update failed, ID not found");
        }
    }
}
