package com.drivo.controller;

import com.drivo.entity.Shipment;
import com.drivo.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping
    public Shipment createShipment(
            @RequestBody Shipment shipment){

        return shipmentService
                .createShipment(shipment);
    }

    @GetMapping
    public List<Shipment> getAllShipments(){

        return shipmentService
                .getAllShipments();
    }
    @GetMapping("/shop/{shopId}")
    public List<Shipment> getShipmentsByShopId(
            @PathVariable Long shopId){

        return shipmentService
                .getShipmentsByShopId(shopId);

    }
}