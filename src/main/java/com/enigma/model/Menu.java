package com.enigma.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "menu_name")
    private String name;

    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private List<MenuPrice> prices;

}
