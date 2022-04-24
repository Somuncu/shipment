package com.trendyol.shipment.repository;

import com.trendyol.shipment.entity.BagStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagStatusRepository extends JpaRepository<BagStatus,Long> {

    BagStatus findByBagId(Long bagId);
}
