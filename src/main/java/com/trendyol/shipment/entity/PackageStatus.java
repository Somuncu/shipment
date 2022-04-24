package com.trendyol.shipment.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Package_Status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class PackageStatus {

    @Id
    @SequenceGenerator(name = "seq_package_status", allocationSize = 1)
    @GeneratedValue(generator = "seq_package_status", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "package_id")
    private Long packageId;

    @Column(name = "status_code")
    private Integer status_code;

    @Column(length = 100, name = "barcode")
    private String barcode;

}
