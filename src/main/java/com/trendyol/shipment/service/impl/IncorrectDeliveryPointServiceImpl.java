package com.trendyol.shipment.service.impl;

import com.trendyol.shipment.entity.BagType;
import com.trendyol.shipment.entity.IncorrectDeliveryPoint;
import com.trendyol.shipment.entity.PackageType;
import com.trendyol.shipment.model.request.IncorrectDeliveryPointRequestType;
import com.trendyol.shipment.repository.IncorrectDeliveryPointRepository;
import com.trendyol.shipment.service.BagService;
import com.trendyol.shipment.service.IncorrectDeliveryPointService;
import com.trendyol.shipment.service.PackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncorrectDeliveryPointServiceImpl implements IncorrectDeliveryPointService {

    @Autowired
    IncorrectDeliveryPointRepository incorrectDeliveryPointRepository;

    @Autowired
    BagService bagService;

    @Autowired
    PackageService packageService;

    Logger logger = LoggerFactory.getLogger(IncorrectDeliveryPointServiceImpl.class);


    @Override
    public void createIncorrectDeliveryPoint(IncorrectDeliveryPointRequestType incorrectDeliveryPointRequestType) {
        try{
            if(incorrectDeliveryPointRepository.findByBarcode(incorrectDeliveryPointRequestType.getBarcode())==null) {
                IncorrectDeliveryPoint incorrectDeliveryPoint = new IncorrectDeliveryPoint();
                incorrectDeliveryPoint.setDeliveryPoint(incorrectDeliveryPointRequestType.getIncorrectDeliveryPointValue());
                incorrectDeliveryPoint.setBarcode(incorrectDeliveryPointRequestType.getBarcode());

                IncorrectDeliveryPoint incorrectDeliveryPointDb = incorrectDeliveryPointRepository.save(incorrectDeliveryPoint);
                logger.info("Incorrect deliverypoint is found : " + incorrectDeliveryPointDb.getBarcode() + "->" + incorrectDeliveryPointRequestType.getIncorrectDeliveryPointValue());
            }
        }catch (Exception ex){
            logger.info(ex.getMessage());
        }
    }

    @Override
    public boolean checkDeliveryPointForBag(Integer deliveryPoint, String barcode) {
        try{

            BagType bagTypeForIncorrectDelivery =  bagService.getBagByBarcode(barcode);
            if(bagTypeForIncorrectDelivery !=null && bagTypeForIncorrectDelivery.getDeliveryPoint()!=deliveryPoint){
                IncorrectDeliveryPointRequestType incorrectDeliveryPointReq = new IncorrectDeliveryPointRequestType();
                incorrectDeliveryPointReq.setIncorrectDeliveryPointValue(deliveryPoint);
                incorrectDeliveryPointReq.setBarcode(barcode);
                createIncorrectDeliveryPoint(incorrectDeliveryPointReq);
                return true;
            }else
                return false;

        }catch (Exception ex){
            logger.info(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean checkDeliveryPointForPackage(Integer deliveryPoint, String barcode) {
        try{

            PackageType packageTypeForIncorrectDelivery =  packageService.getPackageByBarcode(barcode);
            if(packageTypeForIncorrectDelivery !=null && packageTypeForIncorrectDelivery.getDeliveryPoint()!=deliveryPoint){
                IncorrectDeliveryPointRequestType incorrectDeliveryPointReq = new IncorrectDeliveryPointRequestType();
                incorrectDeliveryPointReq.setIncorrectDeliveryPointValue(deliveryPoint);
                incorrectDeliveryPointReq.setBarcode(barcode);
                createIncorrectDeliveryPoint(incorrectDeliveryPointReq);
                return true;
            }
            else
                return false;

        }catch (Exception ex){
            logger.info(ex.getMessage());
            return false;

        }
    }

}
