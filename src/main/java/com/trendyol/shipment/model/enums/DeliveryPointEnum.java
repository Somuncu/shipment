package com.trendyol.shipment.model.enums;

public enum DeliveryPointEnum {
    BRANCH(1), DISTRIBUTION_CENTER(2),TRANSFER_CENTER(3);

    private Integer deliveryPointCode;

    DeliveryPointEnum(Integer deliveryPointCode) {
        this.deliveryPointCode = deliveryPointCode;
    }

    public Integer getBagStatuCode() {
        return deliveryPointCode;
    }
}
