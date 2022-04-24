package com.trendyol.shipment.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequestType {
    @NotEmpty(message = "{barcode.required}")
    private String barcode;
    @NotEmpty(message = "{bagbarcode.required}")
    private String bagBarcode;
}
