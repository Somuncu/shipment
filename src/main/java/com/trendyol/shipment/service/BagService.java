package com.trendyol.shipment.service;

import com.trendyol.shipment.entity.BagType;
import com.trendyol.shipment.model.enums.BagStatusEnum;
import com.trendyol.shipment.model.request.BagRequestType;
import com.trendyol.shipment.model.response.BagResponse;
import org.springframework.stereotype.Service;

@Service
public interface BagService {

    BagResponse createBag(BagRequestType bagRequestType);

    BagType getBagByBarcode(String barcode);

    void updateBagStatus(String barcode, BagStatusEnum bagStatusEnum);
}
