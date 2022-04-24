package com.trendyol.shipment.model.response;

import com.trendyol.shipment.model.RouteState;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipmentResponseType {

    private String plate;
    private ArrayList<RouteState> route;
}
