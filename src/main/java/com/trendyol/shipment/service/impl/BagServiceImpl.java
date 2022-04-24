package com.trendyol.shipment.service.impl;

import com.trendyol.shipment.entity.BagType;
import com.trendyol.shipment.entity.BagStatus;
import com.trendyol.shipment.model.enums.BagStatusEnum;
import com.trendyol.shipment.model.request.BagRequestType;
import com.trendyol.shipment.model.response.BagResponse;
import com.trendyol.shipment.model.response.GeneralResponse;
import com.trendyol.shipment.repository.BagRepository;
import com.trendyol.shipment.repository.BagStatusRepository;
import com.trendyol.shipment.service.BagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BagServiceImpl implements BagService {

    @Autowired
    BagRepository bagRepository;

    @Autowired
    BagStatusRepository bagStatusRepository;

    Logger logger = LoggerFactory.getLogger(BagServiceImpl.class);


    @Override
    @Transactional
    public BagResponse createBag(BagRequestType bagRequestType) {
        BagResponse bagResponse = new BagResponse();

        try {
            if(!checkBagExist(bagRequestType.getBarcode())){
                throw new Exception("This barcode is using.");
            }

            BagType bagType = new BagType();
            bagType.setBarcode(bagRequestType.getBarcode());
            bagType.setDeliveryPoint(bagRequestType.getDeliverPoint());

            BagType bagTypeDb = bagRepository.save(bagType);

            if(bagTypeDb.getId()!=null) {
                GeneralResponse response = createBagStatus(bagTypeDb.getId(), bagTypeDb.getBarcode());
                if(response.getId()==0){
                    throw new Exception(response.getMessage());
                }
            }

            bagResponse.setMessage("Bag is created succesfully.");
            bagResponse.setBagId(bagTypeDb.getId());
        }catch (Exception ex){
            bagResponse.setMessage(ex.getMessage());
        }

        logger.info(bagResponse.getMessage());
        return bagResponse;
    }

    @Override
    public BagType getBagByBarcode(String barcode) {
        return bagRepository.findByBarcode(barcode);
    }

    @Override
    @Transactional
    public void updateBagStatus(String barcode, BagStatusEnum bagStatusEnum) {
        try {
            BagType bagTypeForUpdating = getBagByBarcode(barcode);
            if(bagTypeForUpdating !=null){
                BagStatus bagStatusDb = bagStatusRepository.findByBagId(bagTypeForUpdating.getId());
                if(bagStatusDb !=null){
                    bagStatusDb.setStatus_code(bagStatusEnum.getBagStatuCode());
                    bagStatusRepository.save(bagStatusDb);
                    logger.info("Bag Status is updated for " + bagStatusDb.getBarcode() + "->" + bagStatusEnum.toString());
                }
            }
        }catch (Exception ex){
            logger.info(ex.getMessage());
        }

    }

    @Transactional
     GeneralResponse createBagStatus(Long bagId, String barcode) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            BagStatus bagStatus = new BagStatus();
            bagStatus.setBagId(bagId);
            bagStatus.setStatus_code(BagStatusEnum.CREATED.getBagStatuCode());
            bagStatus.setBarcode(barcode);

            BagStatus bagStatusDb = bagStatusRepository.save(bagStatus);
            generalResponse.setId(bagStatusDb.getId());
            generalResponse.setMessage("Bag Status created successfully");
        }catch (Exception ex){
            generalResponse.setId(0L);
            generalResponse.setMessage(ex.getMessage());
        }
        logger.info(generalResponse.getMessage());
        return generalResponse;

    }

    private boolean checkBagExist(String bagBarcode) {
        BagType bagTypeItem = bagRepository.findByBarcode(bagBarcode);
        return bagTypeItem ==null;
    }
}
