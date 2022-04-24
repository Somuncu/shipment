package com.trendyol.shipment.service.impl;

import com.trendyol.shipment.entity.BagType;
import com.trendyol.shipment.entity.PackageType;
import com.trendyol.shipment.entity.PackageAssignment;
import com.trendyol.shipment.entity.PackageStatus;
import com.trendyol.shipment.model.enums.PackageStatusEnum;
import com.trendyol.shipment.model.request.AssignmentRequestType;
import com.trendyol.shipment.model.response.GeneralResponse;
import com.trendyol.shipment.repository.PackageAssignmentRepository;
import com.trendyol.shipment.repository.PackageStatusRepository;
import com.trendyol.shipment.service.BagService;
import com.trendyol.shipment.service.PackageService;
import com.trendyol.shipment.service.PackagesToBagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PackagesToBagServiceImpl implements PackagesToBagService {

    @Autowired
    PackageAssignmentRepository packageAssignmentRepository;

    @Autowired
    PackageService packageService;

    @Autowired
    BagService bagService;

    @Autowired
    PackageStatusRepository packageStatusRepository;

    Logger logger = LoggerFactory.getLogger(BagServiceImpl.class);



    @Override
    @Transactional
    public GeneralResponse assignToBag(AssignmentRequestType assignmentRequestType) {
        PackageAssignment packageAssignment = new PackageAssignment();
        GeneralResponse generalResponse = new GeneralResponse();

        try {
            PackageType packageTypeItem = checkPackageExist(assignmentRequestType.getBarcode());
            if(packageTypeItem ==null){
                throw new Exception("This package barcode is not exist");
            }
            if(checkBagExist(assignmentRequestType.getBagBarcode())){
                throw new Exception("This bag barcode is not exist");
            }
            packageAssignment.setBarcode(assignmentRequestType.getBarcode());
            packageAssignment.setBagbarcode(assignmentRequestType.getBagBarcode());
            PackageAssignment packageAssignmentDb = packageAssignmentRepository.save(packageAssignment);
            updatePackageStatu(packageTypeItem.getId());

            generalResponse.setId(packageAssignmentDb.getId());
            generalResponse.setMessage("Package assigned to Bag successfully.");
        }catch (Exception ex){
            generalResponse.setMessage(ex.getMessage());
        }

        return generalResponse;
    }


    @Override
    public void updatePackageStatu(Long packageId) {
        try {
            PackageStatus packageStatus = packageStatusRepository.findByPackageId(packageId);
            packageStatus.setStatus_code(PackageStatusEnum.LOADEDINTOBAG.getPackageStatuCode());
            packageStatusRepository.save(packageStatus);
        }catch (Exception ex){
            logger.info(ex.getMessage());
        }

    }

    @Override
    public Boolean packageInBag(String barcode) {
        PackageAssignment packageAssignment = packageAssignmentRepository.findByBarcode(barcode);
        return packageAssignment!=null;
    }

    @Override
    public List<PackageAssignment> getPackagesInBag(String bagbarcode) {
        return packageAssignmentRepository.findAllByBagbarcode(bagbarcode);
    }

    private boolean checkBagExist(String bagBarcode) {
        BagType bagTypeItem = bagService.getBagByBarcode(bagBarcode);
        return bagTypeItem == null;
    }

    private PackageType checkPackageExist(String barcode) {
        PackageType packageTypeItem = packageService.getPackageByBarcode(barcode);
        return packageTypeItem;
    }


}
