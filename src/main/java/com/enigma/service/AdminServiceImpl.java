package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.model.Admin;
import com.enigma.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    private AdminRepository repository;

    public AdminServiceImpl( @Autowired AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Admin create(Admin admin) {
        try{
            return repository.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException("Failed to create admin");
        }
    }

    @Override
    public List<Admin> getAll() {
        List result = repository.findAll();
        if (result.isEmpty()) {
            throw new NotFoundException("Admin not found");
        }
        return result;
    }
}
