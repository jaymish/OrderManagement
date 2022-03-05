package com.order.consumerorder.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Item {
    @Id
    private String id;
    private String item_name;
    private String item_qty;
}
