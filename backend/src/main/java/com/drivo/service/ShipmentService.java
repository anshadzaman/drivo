package com.drivo.service;

import com.drivo.entity.Shipment;
import com.drivo.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    public Shipment createShipment(
            Shipment shipment){

        return shipmentRepository.save(shipment);
    }

    public List<Shipment> getAllShipments(){

        return shipmentRepository.findAll();
    }
    public List<Shipment> getShipmentsByShopId(
            Long shopId){

        return shipmentRepository
                .findByShopId(shopId);

    }
    public void deleteShipment(Long id) {

        shipmentRepository.deleteById(id);

    }
    public Shipment getShipmentById(
            Long id) {

        return shipmentRepository
                .findById(id)
                .orElseThrow();
    }
    public Shipment updateShipment(
            Long id,
            Shipment updatedShipment) {

        Shipment existingShipment =
                shipmentRepository
                        .findById(id)
                        .orElseThrow();

        existingShipment.setPickupLocation(
                updatedShipment.getPickupLocation());

        existingShipment.setDropLocation(
                updatedShipment.getDropLocation());

        existingShipment.setItemName(
                updatedShipment.getItemName());

        return shipmentRepository
                .save(existingShipment);
    }
    public long getShipmentCount() {

        return shipmentRepository.count();

    }
}