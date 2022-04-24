package com.trendyol.shipment.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestType {

    @NotEmpty(message = "{vehicle.required}")
    private String licensePlate;

}
