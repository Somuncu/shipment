package com.trendyol.shipment.service.impl;

import com.trendyol.shipment.entity.Vehicle;
import com.trendyol.shipment.model.request.VehicleRequestType;
import com.trendyol.shipment.model.response.GeneralResponse;
import com.trendyol.shipment.repository.VehicleRepository;
import com.trendyol.shipment.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public GeneralResponse createVehicle(VehicleRequestType vehicleRequestType) {

        Vehicle vehicle = new Vehicle();
        String licensePlate = vehicleRequestType.getLicensePlate().replaceAll("\\s", "");
        vehicle.setLicensePlate(licensePlate);

        Vehicle vehicleDb = vehicleRepository.save(vehicle);

        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setId(vehicleDb.getId());
        generalResponse.setMessage("Vehicle is created successfully.");
        return generalResponse;
    }

    @Override
    public Boolean checkVehicle(String plate) {
        String plateWithoutSpaces = plate.replaceAll("\\s", "");
        Vehicle vehicleExist =  vehicleRepository.findByLicensePlate(plateWithoutSpaces);
        return vehicleExist == null;
    }
}
