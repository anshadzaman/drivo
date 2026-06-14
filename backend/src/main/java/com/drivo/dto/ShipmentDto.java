package com.drivo.dto;

import com.drivo.enums.ShipmentStatus;
import java.time.LocalDateTime;
public class ShipmentDto {

    private Long id;

    private String pickupLocation;

    private String dropLocation;

    private String itemName;

    private ShipmentStatus status;


    private LocalDateTime createdAt;
    private LocalDateTime assignedAt;
    private LocalDateTime pickedUpAt;
    private LocalDateTime inTransitAt;
    private LocalDateTime deliveredAt;
    private UserDto shop;

    private UserDto assignedDriver;

    public ShipmentDto(
            Long id,
            String pickupLocation,
            String dropLocation,
            String itemName,
            ShipmentStatus status,
            UserDto shop,
            UserDto assignedDriver,
            LocalDateTime createdAt,
            LocalDateTime assignedAt,
            LocalDateTime pickedUpAt,
            LocalDateTime inTransitAt,
            LocalDateTime deliveredAt) {

        this.id = id;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.itemName = itemName;
        this.status = status;
        this.shop = shop;
        this.assignedDriver = assignedDriver;

        this.createdAt = createdAt;
        this.assignedAt = assignedAt;
        this.pickedUpAt = pickedUpAt;
        this.inTransitAt = inTransitAt;
        this.deliveredAt = deliveredAt;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public LocalDateTime getPickedUpAt() {
        return pickedUpAt;
    }

    public LocalDateTime getInTransitAt() {
        return inTransitAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
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