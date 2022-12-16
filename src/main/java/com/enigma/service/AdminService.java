package com.enigma.service;

import com.enigma.model.Admin;

import java.util.List;

public interface AdminService {

    Admin create(Admin admin);

    List<Admin> getAll();
}
