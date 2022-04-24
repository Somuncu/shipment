package com.trendyol.shipment.service;

import com.trendyol.shipment.entity.PackageAssignment;
import com.trendyol.shipment.model.request.AssignmentRequestType;
import com.trendyol.shipment.model.response.GeneralResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PackagesToBagService {

    GeneralResponse assignToBag(AssignmentRequestType assignmentRequestType);

    void updatePackageStatu(Long packageId);

    Boolean packageInBag(String barcode);

    List<PackageAssignment> getPackagesInBag(String bagbarcode);
}
