package com.enigma.model.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class AdminRequest {

    private String username;

    private String password;

}
