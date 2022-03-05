package com.order.consumerorder.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Address {
    @Id
    private String id;
    private String name;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private Integer zip;


}
