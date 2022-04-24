package com.trendyol.shipment.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Package_Assignment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class PackageAssignment {

    @Id
    @SequenceGenerator(name = "seq_package_assignment", allocationSize = 1)
    @GeneratedValue(generator = "seq_package_assignment", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "barcode")
    private String barcode;

    @Column(length = 100, name = "bagbarcode")
    private String bagbarcode;

}
