package com.trendyol.shipment.model.request;

import com.trendyol.shipment.model.Route;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
public class ShipmentRequestType {

    @NotEmpty(message = "{plate.required}")
    private String plate;
    @NotNull(message = "{route.required}")
    private ArrayList<Route> route;
}
