package com.trendyol.shipment.service;

import com.trendyol.shipment.entity.PackageType;
import com.trendyol.shipment.model.enums.PackageStatusEnum;
import com.trendyol.shipment.model.request.PackageRequestType;
import com.trendyol.shipment.model.response.PackageResponse;

public interface PackageService {

    PackageResponse createPackage(PackageRequestType packageRequestType);

    PackageType getPackageByBarcode(String barcode);

    void updatePackageStatus(String barcode, PackageStatusEnum packageStatusEnum);

}
