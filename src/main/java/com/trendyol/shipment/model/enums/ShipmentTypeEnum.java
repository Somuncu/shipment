package com.trendyol.shipment.model.enums;



public enum ShipmentTypeEnum {
    PACKAGE(1), BAG(2);

    private Integer shipmentTypeCode;

    ShipmentTypeEnum(Integer shipmentTypeCode) {
        this.shipmentTypeCode = shipmentTypeCode;
    }

    public Integer getShipmentTypeCode() {
        return shipmentTypeCode;
    }
}
