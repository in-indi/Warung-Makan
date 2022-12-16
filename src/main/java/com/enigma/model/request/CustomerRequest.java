package com.enigma.model.request;

import com.enigma.model.Member;
import lombok.Data;

import javax.persistence.Column;

@Data
public class CustomerRequest {

    private String name;

    private String phone;

    private Boolean isMember;

    private Member member;

}
