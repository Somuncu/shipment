package com.trendyol.shipment.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {
    private String fieldName;
    private Object rejectedValue;
    private String messageError;
}