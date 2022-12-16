package com.enigma.model.request;

import com.enigma.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class MemberRequest {

    private String memberType;

    private Customer customer;

}
