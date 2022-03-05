package com.order.consumerorder.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Data
public class DeliveryMethod {
    @Id
    private String id;
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    private Address shippingorpickup;


}
