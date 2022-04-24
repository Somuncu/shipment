package com.trendyol.shipment.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Deliver {
    @NotEmpty(message = "{barcode.required}")
    private String barcode;
}
