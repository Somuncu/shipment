package com.trendyol.shipment.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Delivery_Point")
@Data
public class DeliveryPoint {

    @Id
    @SequenceGenerator(name = "seq_delivery_point", allocationSize = 1)
    @GeneratedValue(generator = "seq_delivery_point", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "license_plate")
    private String deliveryPoint;

    @Column(name = "value")
    private Integer value;


}
