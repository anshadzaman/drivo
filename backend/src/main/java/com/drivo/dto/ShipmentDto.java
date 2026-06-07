package com.drivo.dto;

import com.drivo.enums.ShipmentStatus;

public class ShipmentDto {

    private Long id;

    private String pickupLocation;

    private String dropLocation;

    private String itemName;

    private ShipmentStatus status;

    private UserDto shop;

    private UserDto assignedDriver;

    public ShipmentDto(
            Long id,
            String pickupLocation,
            String dropLocation,
            String itemName,
            ShipmentStatus status,
            UserDto shop,
            UserDto assignedDriver) {

        this.id = id;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.itemName = itemName;
        this.status = status;
        this.shop = shop;
        this.assignedDriver = assignedDriver;
    }

    public Long getId() {
        return id;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public String getItemName() {
        return itemName;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public UserDto getShop() {
        return shop;
    }

    public UserDto getAssignedDriver() {
        return assignedDriver;
    }
}