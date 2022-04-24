package com.trendyol.shipment.service.impl;

import com.trendyol.shipment.entity.PackageAssignment;
import com.trendyol.shipment.model.Deliver;
import com.trendyol.shipment.model.DeliverState;
import com.trendyol.shipment.model.Route;
import com.trendyol.shipment.model.RouteState;
import com.trendyol.shipment.model.enums.BagStatusEnum;
import com.trendyol.shipment.model.enums.DeliveryPointEnum;
import com.trendyol.shipment.model.enums.PackageStatusEnum;
import com.trendyol.shipment.model.enums.ShipmentTypeEnum;
import com.trendyol.shipment.model.request.ShipmentRequestType;
import com.trendyol.shipment.model.response.ShipmentResponseType;
import com.trendyol.shipment.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    PackageService packageService;

    @Autowired
    BagService bagService;

    @Autowired
    PackagesToBagService packagesToBagService;

    @Autowired
    IncorrectDeliveryPointService incorrectDeliveryPointService;

    Logger logger = LoggerFactory.getLogger(ShipmentServiceImpl.class);


    @Override
    @Transactional
    public ShipmentResponseType startShipment(ShipmentRequestType shipmentRequestType) {
        ShipmentResponseType shipmentResponseType = new ShipmentResponseType();
        try {
            if(checkVehiclePlateNumber(shipmentRequestType.getPlate())){
                throw new Exception("Plate is not matched.");
            }
            if(shipmentRequestType.getRoute().size()>0) {
                for (Route routeForShipment : shipmentRequestType.getRoute()) {
                    startLoadingProcess(routeForShipment);
                }
            }else{
                throw new Exception("Route must have an element.");
            }

            shipmentResponseType.setPlate(shipmentRequestType.getPlate());
            ArrayList<RouteState> routeStateList = new ArrayList<>();

            for (Route routeForShipment : shipmentRequestType.getRoute()) {
                RouteState routeStateShipment = getDeliverState(routeForShipment);
                routeStateList.add(routeStateShipment);
            }

            shipmentResponseType.setRoute(routeStateList);


        }catch (Exception ex){
            logger.info(ex.getMessage());
        }

        return shipmentResponseType;
    }

    private RouteState getDeliverState(Route routeForShipment) throws Exception {
        RouteState routeState = new RouteState();
        ArrayList<DeliverState> deliverStateArrayList = new ArrayList<>();
        routeState.setDeliveryPoint(routeForShipment.getDeliveryPoint());

        try {
            if (routeForShipment.getDeliveries().size() > 0) {
                for (Deliver deliverForShipment : routeForShipment.getDeliveries()) {
                    if (deliverForShipment.getBarcode() != null) {
                        char shipmentType = deliverForShipment.getBarcode().charAt(0);
                        DeliverState deliverState = new DeliverState();
                        switch (shipmentType) {
                            case 'P':
                                if(!checkIncorrectDeliveryPointForPackage(routeForShipment.getDeliveryPoint(),deliverForShipment.getBarcode())){
                                    packageService.updatePackageStatus(deliverForShipment.getBarcode(),PackageStatusEnum.UNLOADED);
                                    deliverState.setBarcode(deliverForShipment.getBarcode());
                                    deliverState.setState(PackageStatusEnum.UNLOADED.getPackageStatuCode());
                                }else{
                                    deliverState.setBarcode(deliverForShipment.getBarcode());
                                    deliverState.setState(PackageStatusEnum.LOADED.getPackageStatuCode());
                                }
                                break;

                            case 'C':
                                if(!checkIncorrectDeliveryPointForBag(routeForShipment.getDeliveryPoint(),deliverForShipment.getBarcode())){
                                    bagService.updateBagStatus(deliverForShipment.getBarcode(), BagStatusEnum.UNLOADED);
                                    updateStatusForPackagesInBag(deliverForShipment.getBarcode(),routeForShipment.getDeliveryPoint());
                                    deliverState.setBarcode(deliverForShipment.getBarcode());
                                    deliverState.setState(PackageStatusEnum.UNLOADED.getPackageStatuCode());
                                }else{
                                    deliverState.setBarcode(deliverForShipment.getBarcode());
                                    deliverState.setState(PackageStatusEnum.LOADED.getPackageStatuCode());
                                }
                                break;
                        }
                        deliverStateArrayList.add(deliverState);
                    }
                }
            } else {
                throw new Exception("Deliveries must have packages or bags");
            }
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
        routeState.setDeliveries(deliverStateArrayList);
        return routeState;
    }

    private void updateStatusForPackagesInBag(String barcode, Integer deliveryPoint) {
        List<PackageAssignment> packagesInBag = new ArrayList<>();
        packagesInBag = packagesToBagService.getPackagesInBag(barcode);

        for(PackageAssignment packageAssignment : packagesInBag){
            if(!checkIncorrectDeliveryPointForPackage(deliveryPoint,packageAssignment.getBarcode())){
                packageService.updatePackageStatus(packageAssignment.getBarcode(),PackageStatusEnum.UNLOADED);
            }
        }
    }


    @Transactional
    void startLoadingProcess(Route routeForShipment) throws Exception{
        try {
            if (routeForShipment.getDeliveries().size() > 0) {
                for (Deliver deliverForShipment : routeForShipment.getDeliveries()) {
                    if (deliverForShipment.getBarcode() != null) {
                        char shipmentType = deliverForShipment.getBarcode().charAt(0);

                        switch (shipmentType) {
                            case 'P':
                                packageService.updatePackageStatus(deliverForShipment.getBarcode(),PackageStatusEnum.LOADED);
                                break;

                            case 'C':
                                bagService.updateBagStatus(deliverForShipment.getBarcode(), BagStatusEnum.LOADED);
                                break;
                        }
                    }
                }
            } else {
                throw new Exception("Deliveries must have packages or bags");
            }
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    private boolean checkIncorrectDeliveryPointForBag(Integer deliveryPoint, String barcode) {
        boolean checkIncorrectDeliveryPoint = incorrectDeliveryPointService.checkDeliveryPointForBag(deliveryPoint, barcode);
        if(!checkIncorrectDeliveryPoint)
            return !checkDeliveryPoint(deliveryPoint, ShipmentTypeEnum.BAG,barcode);
        else
            return checkIncorrectDeliveryPoint;
    }

    private boolean checkIncorrectDeliveryPointForPackage(Integer deliveryPoint, String barcode) {
        boolean checkIncorrectDeliveryPoint = incorrectDeliveryPointService.checkDeliveryPointForPackage(deliveryPoint, barcode);
        if(!checkIncorrectDeliveryPoint)
            return !checkDeliveryPoint(deliveryPoint, ShipmentTypeEnum.PACKAGE,barcode);
        else
            return checkIncorrectDeliveryPoint;

    }

    private boolean checkDeliveryPoint(Integer deliveryPoint, ShipmentTypeEnum shipmentTypeEnum,String barcode) {
        Boolean suitableForDeliveryPoint = false;

        if(deliveryPoint == DeliveryPointEnum.BRANCH.getBagStatuCode()){
            if(shipmentTypeEnum.getShipmentTypeCode() == ShipmentTypeEnum.PACKAGE.getShipmentTypeCode() && !packagesToBagService.packageInBag(barcode)){
                suitableForDeliveryPoint = true;
            }else{
                suitableForDeliveryPoint = false;
            }
        }else if(deliveryPoint == DeliveryPointEnum.DISTRIBUTION_CENTER.getBagStatuCode()){
            suitableForDeliveryPoint = true;
        }else if(deliveryPoint == DeliveryPointEnum.TRANSFER_CENTER.getBagStatuCode()){
            if(shipmentTypeEnum.getShipmentTypeCode() == ShipmentTypeEnum.BAG.getShipmentTypeCode() ){
                suitableForDeliveryPoint = true;
            }else{
                suitableForDeliveryPoint = packagesToBagService.packageInBag(barcode);
            }
        }

        return suitableForDeliveryPoint;
    }


    private boolean checkVehiclePlateNumber(String plate) {
        boolean vehicleNotExist = false;
        try {
            vehicleNotExist = vehicleService.checkVehicle(plate);
        }catch (Exception ex){
            logger.info(ex.getMessage());
        }
        return vehicleNotExist;
    }


}
