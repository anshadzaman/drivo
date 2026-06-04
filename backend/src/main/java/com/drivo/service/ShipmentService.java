package com.drivo.service;

import com.drivo.entity.Shipment;
import com.drivo.entity.User;
import com.drivo.repository.ShipmentRepository;
import com.drivo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.drivo.enums.ShipmentStatus;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    public Shipment createShipment(
            Shipment shipment,
            String email){

        User shop =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        shipment.setShop(shop);

        shipment.setStatus(
                ShipmentStatus.PENDING);

        return shipmentRepository.save(
                shipment);
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
    public Shipment markAsDelivered(
            Long id) {

        Shipment shipment =
                shipmentRepository
                        .findById(id)
                        .orElseThrow();

        shipment.setStatus(
                ShipmentStatus.DELIVERED);

        return shipmentRepository
                .save(shipment);
    }
    public Shipment markMyShipmentDelivered(
            Long shipmentId,
            String email) {

        Shipment shipment =
                shipmentRepository
                        .findById(shipmentId)
                        .orElseThrow();

        User driver =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        if (shipment.getAssignedDriver() == null) {

            throw new RuntimeException(
                    "No driver assigned");
        }

        if (!shipment
                .getAssignedDriver()
                .getId()
                .equals(driver.getId())) {

            throw new RuntimeException(
                    "You are not assigned to this shipment");
        }

        shipment.setStatus(
                ShipmentStatus.DELIVERED);

        return shipmentRepository
                .save(shipment);
    }
    public long getPendingCount() {

        return shipmentRepository
                .countByStatus(
                        ShipmentStatus.PENDING);
    }
    public long getDeliveredCount() {

        return shipmentRepository
                .countByStatus(
                        ShipmentStatus.DELIVERED);
    }
    public Shipment assignDriver(
            Long shipmentId,
            Long driverId) {

        Shipment shipment =
                shipmentRepository
                        .findById(shipmentId)
                        .orElseThrow();

        User driver =
                userRepository
                        .findById(driverId)
                        .orElseThrow();

        shipment.setAssignedDriver(
                driver);

        return shipmentRepository
                .save(shipment);
    }
    public List<Shipment>
    getMyShipments(
            String email) {

        User driver =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return shipmentRepository
                .findByAssignedDriverId(
                        driver.getId());
    }
    public List<Shipment>
    getMyShopShipments(
            String email){

        User shop =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return shipmentRepository
                .findByShopId(
                        shop.getId());
    }
    public long getMyShipmentCount(
            String email) {

        User shop =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return shipmentRepository
                .countByShopId(
                        shop.getId());
    }
    public long getMyPendingCount(
            String email) {

        User shop =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return shipmentRepository
                .countByShopIdAndStatus(
                        shop.getId(),
                        ShipmentStatus.PENDING);
    }
    public long getMyDeliveredCount(
            String email) {

        User shop =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return shipmentRepository
                .countByShopIdAndStatus(
                        shop.getId(),
                        ShipmentStatus.DELIVERED);
    }
}