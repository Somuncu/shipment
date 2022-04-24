package com.trendyol.shipment.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Bag_Status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class BagStatus {

    @Id
    @SequenceGenerator(name = "seq_bag_status", allocationSize = 1)
    @GeneratedValue(generator = "seq_bag_status", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "bag_id")
    private Long bagId;

    @Column(name = "status_code")
    private Integer status_code;

    @Column(length = 100, name = "barcode")
    private String barcode;

}
