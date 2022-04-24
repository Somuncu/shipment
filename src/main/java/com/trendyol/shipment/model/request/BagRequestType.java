package com.trendyol.shipment.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BagRequestType {

    private String barcode;
    private Integer deliverPoint;

}
