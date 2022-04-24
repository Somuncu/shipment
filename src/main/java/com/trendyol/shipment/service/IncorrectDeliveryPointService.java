package com.trendyol.shipment.service;


import com.trendyol.shipment.model.request.IncorrectDeliveryPointRequestType;

public interface IncorrectDeliveryPointService {

     void createIncorrectDeliveryPoint(IncorrectDeliveryPointRequestType incorrectDeliveryPointRequestType);

     boolean checkDeliveryPointForBag(Integer deliveryPoint, String barcode);

     boolean checkDeliveryPointForPackage(Integer deliveryPoint, String barcode);

}
