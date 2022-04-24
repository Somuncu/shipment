package com.trendyol.shipment.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Incorrect_Delivery_Point")
@Data
public class IncorrectDeliveryPoint {

    @Id
    @SequenceGenerator(name = "seq_incorrect_delivery_point", allocationSize = 1)
    @GeneratedValue(generator = "seq_incorrect_delivery_point", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "delivery_point")
    private Integer deliveryPoint;

    @Column(length = 100, name = "barcode")
    private String barcode;



}
