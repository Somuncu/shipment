package com.trendyol.shipment.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BagResponse {
    private Long bagId;
    private String message;
}
