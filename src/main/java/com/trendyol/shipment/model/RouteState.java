package com.trendyol.shipment.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;

@Data
@Validated
public class RouteState {
    private Integer deliveryPoint;
    private ArrayList<DeliverState> deliveries;
}
