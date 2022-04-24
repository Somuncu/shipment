package com.trendyol.shipment.model.enums;



public enum BagStatusEnum {
    CREATED(1), LOADED(3),UNLOADED(4);

    private Integer bagStatuCode;

    BagStatusEnum(Integer bagStatuCode) {
        this.bagStatuCode = bagStatuCode;
    }

    public Integer getBagStatuCode() {
        return bagStatuCode;
    }
}
