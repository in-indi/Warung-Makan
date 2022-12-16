package com.enigma.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Data
public class Admin {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "admin_id", nullable = false)
    private String adminId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}
