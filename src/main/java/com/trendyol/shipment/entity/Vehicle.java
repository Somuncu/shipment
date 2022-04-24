package com.trendyol.shipment.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Vehicle")
@Data
public class Vehicle {

    @Id
    @SequenceGenerator(name = "seq_vehicle", allocationSize = 1)
    @GeneratedValue(generator = "seq_vehicle", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "license_plate")
    private String licensePlate;


}
