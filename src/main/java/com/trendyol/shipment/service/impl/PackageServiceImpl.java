package com.trendyol.shipment.service.impl;

import com.trendyol.shipment.entity.PackageType;
import com.trendyol.shipment.entity.PackageStatus;
import com.trendyol.shipment.model.enums.PackageStatusEnum;
import com.trendyol.shipment.model.request.PackageRequestType;
import com.trendyol.shipment.model.response.GeneralResponse;
import com.trendyol.shipment.model.response.PackageResponse;
import com.trendyol.shipment.repository.PackageRepository;
import com.trendyol.shipment.repository.PackageStatusRepository;
import com.trendyol.shipment.service.PackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    PackageStatusRepository packageStatusRepository;

    Logger logger = LoggerFactory.getLogger(PackageServiceImpl.class);



    @Override
    @Transactional
    public PackageResponse createPackage(PackageRequestType packageRequestType) {
        PackageResponse packageResponse = new PackageResponse();

        try{
            if(!checkPackageExist(packageRequestType.getBarcode())){
                throw new Exception("This barcode is using");
            }

            PackageType packageTypeItem = new PackageType();
            packageTypeItem.setBarcode(packageRequestType.getBarcode());
            packageTypeItem.setDeliveryPoint(packageRequestType.getDeliverPoint());
            packageTypeItem.setVolumetricWeight(packageRequestType.getVolumetricWeight());

            PackageType packageTypeDb = packageRepository.save(packageTypeItem);
            if(packageTypeDb.getId()!=null) {
                GeneralResponse response = createPackageStatus(packageTypeDb.getId(), packageTypeDb.getBarcode());
                if(response.getId()==0){
                    throw new Exception(response.getMessage());
                }
            }
            packageResponse.setPackageId(packageTypeDb.getId());
            packageResponse.setMessage("Package is created successfully");

        }catch (Exception ex){
            packageResponse.setMessage(ex.getMessage());
        }
        return packageResponse;
    }

    @Override
    public PackageType getPackageByBarcode(String barcode) {
        return packageRepository.findByBarcode(barcode);
    }

    @Override
    @Transactional
    public void updatePackageStatus(String barcode, PackageStatusEnum packageStatusEnum) {
        try {
            PackageType packageTypeForUpdating = getPackageByBarcode(barcode);
            if(packageTypeForUpdating !=null){
                PackageStatus packageStatus = packageStatusRepository.findByPackageId(packageTypeForUpdating.getId());
                if(packageStatus!=null) {
                    packageStatus.setStatus_code(packageStatusEnum.getPackageStatuCode());
                    PackageStatus packageStatusDb = packageStatusRepository.save(packageStatus);
                    logger.info("Package Status is updated for " + packageStatusDb.getBarcode());
                }
            }
        }catch (Exception ex){
           logger.info(ex.getMessage());
        }

    }

    @Transactional
    GeneralResponse createPackageStatus(Long packageId,String barcode) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            PackageStatus packageStatus = new PackageStatus();
            packageStatus.setPackageId(packageId);
            packageStatus.setStatus_code(PackageStatusEnum.CREATED.getPackageStatuCode());
            packageStatus.setBarcode(barcode);

            PackageStatus packageStatusDb = packageStatusRepository.save(packageStatus);

            generalResponse.setId(packageStatusDb.getId());
            generalResponse.setMessage("Package Status created successfully");
        }catch (Exception ex){
            generalResponse.setId(0L);
            generalResponse.setMessage(ex.getMessage());
        }
        logger.info(generalResponse.getMessage());
        return generalResponse;

    }

    private boolean checkPackageExist(String barcode) {
        PackageType packageTypeItem = packageRepository.findByBarcode(barcode);
        return packageTypeItem ==null;
    }
}
