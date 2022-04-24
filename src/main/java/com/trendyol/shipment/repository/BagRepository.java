package com.trendyol.shipment.repository;

import com.trendyol.shipment.entity.BagType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagRepository extends JpaRepository<BagType,Long> {

    BagType findByBarcode(String barcode);
}
