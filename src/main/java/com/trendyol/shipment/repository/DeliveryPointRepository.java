package com.trendyol.shipment.repository;

import com.trendyol.shipment.entity.DeliveryPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint,Long> {
}
