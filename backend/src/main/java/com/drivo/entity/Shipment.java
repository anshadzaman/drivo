package com.drivo.entity;

import com.drivo.enums.ShipmentStatus;
import jakarta.persistence.*;
import com.drivo.entity.User;

import java.time.LocalDateTime;

@Entity
@Table(name="shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime createdAt;

    private LocalDateTime assignedAt;

    private LocalDateTime pickedUpAt;

    private LocalDateTime inTransitAt;

    private LocalDateTime deliveredAt;
    private String pickupLocation;

    private String dropLocation;

    private String itemName;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getPickupLocation(){
        return pickupLocation;
    }

    public void setPickupLocation(
            String pickupLocation){

        this.pickupLocation=
                pickupLocation;
    }

    public String getDropLocation(){
        return dropLocation;
    }

    public void setDropLocation(
            String dropLocation){

        this.dropLocation=
                dropLocation;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(
            String itemName){

        this.itemName=itemName;
    }

    public ShipmentStatus getStatus(){

        return status;
    }

    public void setStatus(
            ShipmentStatus status){

        this.status=status;
    }

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private User shop;

    public User getShop() {
        return shop;
    }

    public void setShop(User shop) {
        this.shop = shop;
    }
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User assignedDriver;

    public User getAssignedDriver() {
        return assignedDriver;
    }

    public void setAssignedDriver(
            User assignedDriver) {

        this.assignedDriver = assignedDriver;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt) {

        this.createdAt = createdAt;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(
            LocalDateTime assignedAt) {

        this.assignedAt = assignedAt;
    }

    public LocalDateTime getPickedUpAt() {
        return pickedUpAt;
    }

    public void setPickedUpAt(
            LocalDateTime pickedUpAt) {

        this.pickedUpAt = pickedUpAt;
    }

    public LocalDateTime getInTransitAt() {
        return inTransitAt;
    }

    public void setInTransitAt(
            LocalDateTime inTransitAt) {

        this.inTransitAt = inTransitAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(
            LocalDateTime deliveredAt) {

        this.deliveredAt = deliveredAt;
    }

}