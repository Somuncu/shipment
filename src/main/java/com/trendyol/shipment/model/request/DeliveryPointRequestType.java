package com.trendyol.shipment.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryPointRequestType {

    private String deliveryPoint;
    private Integer value;

}
