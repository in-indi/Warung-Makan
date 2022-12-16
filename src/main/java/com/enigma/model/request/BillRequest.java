package com.enigma.model.request;

import com.enigma.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class BillRequest {

    private Date date;

    private Customer customer;
}
