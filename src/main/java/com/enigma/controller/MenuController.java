package com.enigma.controller;

import com.enigma.constants.UrlMappings;
import com.enigma.model.Member;
import com.enigma.model.Menu;
import com.enigma.model.request.MemberRequest;
import com.enigma.model.request.MenuRequest;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.MenuService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMappings.MEMBER_URL)
public class MenuController {

    private ModelMapper modelMapper;

    private MenuService service;

    public MenuController(
            @Autowired ModelMapper modelMapper,
            @Autowired MenuService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity createMenu(@RequestBody MenuRequest request){
        Menu newMenu = modelMapper.map(request, Menu.class);

        Menu result = service.create(newMenu);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>( "Success add menu",result));
    }

    @GetMapping
    public ResponseEntity getAllMenu() {

        List<Menu> result= service.getAll();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>( "Success get all menu",result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Menu menu  = service.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find ID", menu));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody MenuRequest request, @PathVariable("id") Long id) {
        Menu existingMenu = modelMapper.map(request, Menu.class);
        existingMenu.setId(id);
        service.update(existingMenu, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully update", request));
    }
}
