package com.drivo.controller;

import com.drivo.dto.ShipmentDto;
import com.drivo.entity.Shipment;
import com.drivo.entity.User;
import com.drivo.enums.Role;
import com.drivo.service.ShipmentService;
import com.drivo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Shipment createShipment(
            @RequestBody Shipment shipment) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return shipmentService
                .createShipment(
                        shipment,
                        email);
    }

    @GetMapping
    public List<ShipmentDto>
    getAllShipments() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return shipmentService
                .getMyShopShipments(
                        email);
    }

    @GetMapping("/shop/{shopId}")
    public List<Shipment> getShipmentsByShopId(
            @PathVariable Long shopId) {

        return shipmentService
                .getShipmentsByShopId(shopId);

    }

    @DeleteMapping("/{id}")
    public void deleteShipment(
            @PathVariable Long id) {

        shipmentService.deleteShipment(id);
    }

    @GetMapping("/{id}")
    public ShipmentDto getShipmentById(
            @PathVariable Long id) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return shipmentService
                .getShipmentById(
                        id,
                        email);
    }

    @PutMapping("/{id}")
    public Shipment updateShipment(
            @PathVariable Long id,
            @RequestBody Shipment shipment) {

        return shipmentService
                .updateShipment(id, shipment);
    }

    @GetMapping("/count")
    public long getShipmentCount() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userService
                        .getUserByEmail(email);

        if (user.getRole() == Role.ADMIN) {

            return shipmentService
                    .getShipmentCount();

        }

        return shipmentService
                .getMyShipmentCount(email);
    }

    @PutMapping("/{id}/deliver")
    public Shipment markAsDelivered(
            @PathVariable Long id) {

        return shipmentService
                .markAsDelivered(id);
    }

    @GetMapping("/count/pending")
    public long getPendingCount() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userService
                        .getUserByEmail(email);

        if (user.getRole() == Role.ADMIN) {

            return shipmentService
                    .getPendingCount();

        }

        return shipmentService
                .getMyPendingCount(
                        email);
    }

    @GetMapping("/count/delivered")
    public long getDeliveredCount() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userService
                        .getUserByEmail(email);

        if (user.getRole() == Role.ADMIN) {

            return shipmentService
                    .getDeliveredCount();

        }

        return shipmentService
                .getMyDeliveredCount(
                        email);
    }

    @PutMapping(
            "/{shipmentId}/assign-driver/{driverId}")
    public Shipment assignDriver(

            @PathVariable Long shipmentId,

            @PathVariable Long driverId) {

        return shipmentService
                .assignDriver(
                        shipmentId,
                        driverId);
    }

    @GetMapping("/my-shipments")
    public List<ShipmentDto>
    getMyShipments() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return shipmentService
                .getMyShipments(
                        email);
    }

    @PutMapping("/{id}/my-deliver")
    public Shipment markMyShipmentDelivered(
            @PathVariable Long id) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return shipmentService
                .markMyShipmentDelivered(
                        id,
                        email);
    }

    @GetMapping("/my-count")
    public long getMyAssignedShipmentCount() {
        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();
        return shipmentService
                .getMyAssignedShipmentCount(email);
    }
    @GetMapping("/my-count/delivered")
    public long getMyDeliveredShipmentCount(){
        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();
        return shipmentService
                .getMyDeliveredShipmentCount(email);
    }
    @GetMapping("/my-count/pending")
    public long getMyPendingShipmentCount(){
        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();
        return shipmentService
                .getMyPendingShipmentCount(email);
    }
    @PutMapping("/{id}/pickup")
    public Shipment markPickedUp(
            @PathVariable Long id) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return shipmentService
                .markPickedUp(
                        id,
                        email);
    }
    @PutMapping("/{id}/in-transit")
    public Shipment markInTransit(
            @PathVariable Long id) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return shipmentService
                .markInTransit(
                        id,
                        email);
    }
}