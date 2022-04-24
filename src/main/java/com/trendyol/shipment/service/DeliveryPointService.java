package com.trendyol.shipment.service;


import com.trendyol.shipment.model.request.DeliveryPointRequestType;
import com.trendyol.shipment.model.response.GeneralResponse;

public interface DeliveryPointService {

     GeneralResponse createDeliveryPoint(DeliveryPointRequestType deliveryPointRequestType);

}
