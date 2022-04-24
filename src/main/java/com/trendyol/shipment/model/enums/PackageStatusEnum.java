package com.trendyol.shipment.model.enums;

public enum PackageStatusEnum {
    CREATED(1), LOADEDINTOBAG(2),LOADED(3),UNLOADED(4);

    private Integer packageStatuCode;

    PackageStatusEnum(Integer packageStatuCode) {
        this.packageStatuCode = packageStatuCode;
    }

    public Integer getPackageStatuCode() {
        return packageStatuCode;
    }
}
