package com.trendyol.shipment.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageRequestType {

    @NotNull(message = "{barcode.required}")
    private String barcode;
    private Integer deliverPoint;
    private Integer volumetricWeight;

}
