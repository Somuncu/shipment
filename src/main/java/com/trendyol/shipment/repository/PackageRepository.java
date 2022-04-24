package com.trendyol.shipment.repository;

import com.trendyol.shipment.entity.PackageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<PackageType,Long> {

    PackageType findByBarcode(String barcode);

}
