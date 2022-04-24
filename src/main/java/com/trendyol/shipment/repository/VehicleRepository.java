package com.trendyol.shipment.repository;

import com.trendyol.shipment.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Vehicle findByLicensePlate(String licencePlate);
}
