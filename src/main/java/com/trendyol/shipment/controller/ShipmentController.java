package com.trendyol.shipment.controller;

import com.trendyol.shipment.model.ErrorModel;
import com.trendyol.shipment.model.request.*;
import com.trendyol.shipment.model.response.*;
import com.trendyol.shipment.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/shipment")
@RestController
@AllArgsConstructor
public class ShipmentController {

    private PackagesToBagService packagesToBagService;

    private PackageService packageService;

    private BagService bagService;

    private VehicleService vehicleService;

    private DeliveryPointService deliveryPointService;

    private ShipmentService shipmentService;




    @PostMapping("/assignment")
    private ResponseEntity<GeneralResponse> assignToBag(@Valid @RequestBody AssignmentRequestType assignmentRequestType){
        return new ResponseEntity<>(packagesToBagService.assignToBag(assignmentRequestType), HttpStatus.OK);
    }


    @PostMapping("/packages")
    private ResponseEntity<PackageResponse> createPackage(@Valid @RequestBody PackageRequestType packageRequestType){
        return new ResponseEntity<>(packageService.createPackage(packageRequestType), HttpStatus.OK);
    }


    @PostMapping("/bags")
    private ResponseEntity<BagResponse> createBag(@Valid @RequestBody BagRequestType bagRequestType){
        return new ResponseEntity<>(bagService.createBag(bagRequestType), HttpStatus.OK);
    }

    @PostMapping("/vehicle")
    private ResponseEntity<GeneralResponse> createVehicle(@Valid @RequestBody VehicleRequestType vehicleRequestType){
        return new ResponseEntity<>(vehicleService.createVehicle(vehicleRequestType), HttpStatus.OK);
    }

    @PostMapping("/deliverypoint")
    private ResponseEntity<GeneralResponse> createDeliveryPoint(@Valid @RequestBody DeliveryPointRequestType deliveryPoint){
        return new ResponseEntity<>(deliveryPointService.createDeliveryPoint(deliveryPoint), HttpStatus.OK);
    }

    @PostMapping("/loading")
    private ResponseEntity<ShipmentResponseType> startShipment(@Valid @RequestBody ShipmentRequestType shipmentRequestType){
        return new ResponseEntity<>(shipmentService.startShipment(shipmentRequestType), HttpStatus.OK);
    }

//    @GetMapping()
//    private ResponseEntity<ShipmentRequestType> shipment(){
//        return new ResponseEntity<>(shipmentService.getShipment(), HttpStatus.OK);
//    }

    /**
     * Method that check against {@code @Valid} Objects passed to controller endpoints
     *
     * @param exception
     * @return a {@code ErrorResponse}
     * @see ErrorResponse
     */
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {

        List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());
        return ErrorResponse.builder().errorMessage(errorMessages).build();
    }
}
