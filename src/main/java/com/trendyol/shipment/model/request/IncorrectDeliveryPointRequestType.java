package com.trendyol.shipment.model.request;

import lombok.Data;

@Data
public class IncorrectDeliveryPointRequestType {
    private Integer incorrectDeliveryPointValue;
    private String barcode;
}
