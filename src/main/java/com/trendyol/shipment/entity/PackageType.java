package com.trendyol.shipment.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Packages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class PackageType {

    @Id
    @SequenceGenerator(name = "seq_package", allocationSize = 1)
    @GeneratedValue(generator = "seq_package", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "barcode")
    private String barcode;

    @Column(name = "deliveryPoint")
    private Integer deliveryPoint;

    @Column(name = "volumetricWeight")
    private Integer volumetricWeight;
}
