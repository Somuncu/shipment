package com.trendyol.shipment.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Bag")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class BagType {

    @Id
    @SequenceGenerator(name = "seq_bag", allocationSize = 1)
    @GeneratedValue(generator = "seq_bag", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "barcode")
    private String barcode;

    @Column(name = "deliveryPoint")
    private Integer deliveryPoint;
}
