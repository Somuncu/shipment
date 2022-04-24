package com.trendyol.shipment.service;

import com.trendyol.shipment.model.request.ShipmentRequestType;
import com.trendyol.shipment.model.response.ShipmentResponseType;
import org.springframework.stereotype.Service;

@Service
public interface ShipmentService {

    ShipmentResponseType startShipment(ShipmentRequestType shipmentRequestType);

    //ShipmentRequestType getShipment();
}
