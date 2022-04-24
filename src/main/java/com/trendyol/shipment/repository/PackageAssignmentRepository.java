package com.trendyol.shipment.repository;

import com.trendyol.shipment.entity.PackageAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageAssignmentRepository extends JpaRepository<PackageAssignment,Long> {

    PackageAssignment findByBarcode(String barcode);

    List<PackageAssignment> findAllByBagbarcode(String barcode);

}
