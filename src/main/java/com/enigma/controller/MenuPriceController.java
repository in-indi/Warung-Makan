package com.enigma.controller;

import com.enigma.constants.UrlMappings;
import com.enigma.model.Customer;
import com.enigma.model.MenuPrice;
import com.enigma.model.request.CustomerRequest;
import com.enigma.model.request.MenuPriceRequest;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.MenuPriceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMappings.PRICE_URL)
public class MenuPriceController {

    private ModelMapper modelMapper;

    private MenuPriceService service;

    public MenuPriceController(
            @Autowired ModelMapper modelMapper,
            @Autowired MenuPriceService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity createPrice(@RequestBody MenuPriceRequest request) {
        MenuPrice newPrice = modelMapper.map(request, MenuPrice.class);

        MenuPrice result = service.create(newPrice);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>( "Success add customer",result));
    }

    @GetMapping
    public ResponseEntity getAllPrice() {

        List<MenuPrice> result= service.getAll();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>( "Success get all customers",result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        MenuPrice price = service.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find ID", price));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody MenuPriceRequest request, @PathVariable("id") Long id) {
        MenuPrice existingPrice = modelMapper.map(request, MenuPrice.class);
        existingPrice.setId(id);
        service.update(existingPrice, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully update", request));
    }


}
