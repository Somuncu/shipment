package com.trendyol.shipment.service;

import com.trendyol.shipment.model.request.VehicleRequestType;
import com.trendyol.shipment.model.response.GeneralResponse;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {

    GeneralResponse createVehicle(VehicleRequestType vehicleRequestType);

    Boolean checkVehicle(String plate);

}
