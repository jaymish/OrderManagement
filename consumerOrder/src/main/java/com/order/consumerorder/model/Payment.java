package com.order.consumerorder.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
//@NoArgsConstructor
@Entity
public class Payment {
    @Id
    private String id;
    private String method;
    private Date date;
    private Double confirmation_number;
    @OneToOne(cascade = CascadeType.ALL)
    private Address billing;


}
