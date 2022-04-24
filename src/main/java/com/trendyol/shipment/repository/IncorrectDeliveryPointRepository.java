package com.trendyol.shipment.repository;

import com.trendyol.shipment.entity.IncorrectDeliveryPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncorrectDeliveryPointRepository extends JpaRepository<IncorrectDeliveryPoint,Long> {
    IncorrectDeliveryPoint findByBarcode(String barcode);
}
