package com.enigma.repository;

import com.enigma.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query(value = "SELECT * FROM admin", nativeQuery = true)
    List<Admin> findAll();

    @Modifying
    @Query(value = "INSERT INTO admin(username, password) VALUES (:username, :password)", nativeQuery = true)
    void create(String username, String password);
}
