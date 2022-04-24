package com.trendyol.shipment.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
@Validated
public class Route {
    @NotEmpty(message = "{deliverypoint.required}")
    private Integer deliveryPoint;
    @NotNull(message = "{deliveries.required}")
    private ArrayList<Deliver> deliveries;
}
