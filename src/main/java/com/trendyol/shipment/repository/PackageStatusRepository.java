package com.trendyol.shipment.repository;

import com.trendyol.shipment.entity.PackageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageStatusRepository extends JpaRepository<PackageStatus,Long> {

    PackageStatus findByPackageId(Long packageId);
}
