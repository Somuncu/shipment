package com.trendyol.shipment.service.impl;

import com.trendyol.shipment.entity.DeliveryPoint;
import com.trendyol.shipment.model.request.DeliveryPointRequestType;
import com.trendyol.shipment.model.response.GeneralResponse;
import com.trendyol.shipment.repository.DeliveryPointRepository;
import com.trendyol.shipment.service.DeliveryPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPointServiceImpl implements DeliveryPointService {

    @Autowired
    DeliveryPointRepository deliveryPointRepository;

    @Override
    public GeneralResponse createDeliveryPoint(DeliveryPointRequestType deliveryPointRequestType) {
        DeliveryPoint deliveryPoint = new DeliveryPoint();
        deliveryPoint.setDeliveryPoint(deliveryPointRequestType.getDeliveryPoint());
        deliveryPoint.setValue(deliveryPointRequestType.getValue());


        DeliveryPoint deliveryPointDb = deliveryPointRepository.save(deliveryPoint);

        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setId(deliveryPointDb.getId());
        generalResponse.setMessage("Success");
        return generalResponse;
    }

}
