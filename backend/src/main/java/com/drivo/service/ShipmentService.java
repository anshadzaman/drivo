package com.drivo.service;

import com.drivo.dto.ShipmentDto;
import com.drivo.dto.UserDto;
import com.drivo.entity.Shipment;
import com.drivo.entity.User;
import com.drivo.enums.NotificationType;
import com.drivo.enums.Role;
import com.drivo.repository.ShipmentRepository;
import com.drivo.repository.UserRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.drivo.enums.ShipmentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationService
            notificationService;
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
        shipment.setCreatedAt(
                LocalDateTime.now());

        return shipmentRepository.save(
                shipment);
    }

    public List<ShipmentDto>
    getAllShipments() {

        return shipmentRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .toList();

    }
    public List<Shipment> getShipmentsByShopId(
            Long shopId){

        return shipmentRepository
                .findByShopId(shopId);

    }
    public void deleteShipment(Long id) {

        shipmentRepository.deleteById(id);

    }

    public ShipmentDto
    getShipmentById(
            Long id,
            String email) {

        Shipment shipment =
                shipmentRepository
                        .findById(id)
                        .orElseThrow();

        validateShipmentAccess(
                shipment,
                email);

        return toDto(
                shipment);
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
        shipment.setDeliveredAt(
                LocalDateTime.now());
        notificationService.createNotification(
                shipment.getShop(),
                "Shipment #" +
                        shipment.getId() +
                        " (" +
                        shipment.getItemName() +
                        ") has been delivered",
                NotificationType.DELIVERED
        );
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
        shipment.setDeliveredAt(
                LocalDateTime.now()
        );
        notificationService.createNotification(
                shipment.getShop(),
                "Shipment #" +
                        shipment.getId() +
                        " (" +
                        shipment.getItemName() +
                        ") has been delivered",
                NotificationType.DELIVERED
        );
        return shipmentRepository
                .save(shipment);
    }
    public long getPendingCount() {

        return shipmentRepository
                .countByStatusIn(
                        List.of(
                                ShipmentStatus.PENDING,
                                ShipmentStatus.ASSIGNED,
                                ShipmentStatus.PICKED_UP,
                                ShipmentStatus.IN_TRANSIT
                        )
                );
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
        shipment.setStatus(
                ShipmentStatus.ASSIGNED);
        shipment.setAssignedAt(
                LocalDateTime.now());
        notificationService
                .createNotification(

                        driver,

                        "Shipment #"
                                + shipment.getId()+
                                " (" +
                                shipment.getItemName()
                                +  ") assigned to you",
                        NotificationType.ASSIGNMENT

                );
        return shipmentRepository
                .save(shipment);
    }

    public List<ShipmentDto>
    getMyShipments(
            String email) {

        User driver =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return shipmentRepository
                .findByAssignedDriverId(
                        driver.getId())
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<ShipmentDto>
    getMyShopShipments(
            String email){

        User shop =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return shipmentRepository
                .findByShopId(
                        shop.getId())
                .stream()
                .map(this::toDto)
                .toList();
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
                .countByShopIdAndStatusIn(
                        shop.getId(),
                        List.of(
                                ShipmentStatus.PENDING,
                                ShipmentStatus.ASSIGNED,
                                ShipmentStatus.PICKED_UP,
                                ShipmentStatus.IN_TRANSIT
                        )
                );
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
    public long getMyAssignedShipmentCount(String email){
        User driver = userRepository
                .findByEmail(email)
                .orElseThrow();
        return shipmentRepository
                .countByAssignedDriverId(
                        driver.getId()
                );
    }
    public long getMyPendingShipmentCount(
            String email) {

        User driver =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return shipmentRepository
                .countByAssignedDriverIdAndStatusIn(
                        driver.getId(),
                        List.of(
                                ShipmentStatus.ASSIGNED,
                                ShipmentStatus.PICKED_UP,
                                ShipmentStatus.IN_TRANSIT
                        )
                );
    }
    public long getMyDeliveredShipmentCount(String email){
        User driver = userRepository
                .findByEmail(email)
                .orElseThrow();
        return shipmentRepository
                .countByAssignedDriverIdAndStatus(
                        driver.getId(),
                        ShipmentStatus.DELIVERED
                );

    }
    private ShipmentDto toDto(
            Shipment shipment) {

        UserDto shopDto =
                new UserDto(

                        shipment.getShop().getId(),

                        shipment.getShop().getName(),

                        shipment.getShop().getEmail(),

                        shipment.getShop()
                                .getRole()
                                .name()

                );

        UserDto driverDto = null;

        if (shipment.getAssignedDriver()
                != null) {

            driverDto =
                    new UserDto(

                            shipment
                                    .getAssignedDriver()
                                    .getId(),

                            shipment
                                    .getAssignedDriver()
                                    .getName(),

                            shipment
                                    .getAssignedDriver()
                                    .getEmail(),

                            shipment
                                    .getAssignedDriver()
                                    .getRole()
                                    .name()

                    );

        }

        return new ShipmentDto(

                shipment.getId(),

                shipment.getPickupLocation(),

                shipment.getDropLocation(),

                shipment.getItemName(),

                shipment.getStatus(),

                shopDto,

                driverDto,
                shipment.getCreatedAt(),
                shipment.getAssignedAt(),
                shipment.getPickedUpAt(),
                shipment.getInTransitAt(),
                shipment.getDeliveredAt()

        );

    }
    private void validateShipmentAccess(
            Shipment shipment,
            String email) {

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        if (user.getRole()
                == Role.ADMIN) {

            return;
        }

        if (user.getRole()
                == Role.SHOP) {

            if (shipment.getShop()
                    .getId()
                    .equals(user.getId())) {

                return;
            }

        }

        if (user.getRole()
                == Role.DRIVER) {

            if (shipment.getAssignedDriver()
                    != null

                    &&

                    shipment
                            .getAssignedDriver()
                            .getId()
                            .equals(
                                    user.getId()
                            )) {

                return;
            }

        }

        throw new org.springframework.web.server.ResponseStatusException(

                org.springframework.http.HttpStatus.FORBIDDEN,

                "Access Denied"

        );

    }
    public Shipment markPickedUp(
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
                ShipmentStatus.PICKED_UP);
        shipment.setPickedUpAt(
                LocalDateTime.now());
        notificationService.createNotification(
                shipment.getShop(),
                "Shipment #" +
                        shipment.getId()+
                        " (" +
                        shipment.getItemName() +
                        ") has been picked up",
                NotificationType.PICKUP
        );
        return shipmentRepository
                .save(shipment);
    }
    public Shipment markInTransit(
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
                ShipmentStatus.IN_TRANSIT);
        shipment.setInTransitAt(
                LocalDateTime.now());
        notificationService.createNotification(
                shipment.getShop(),
                "Shipment #" +
                        shipment.getId()
                        +
                        " (" +
                        shipment.getItemName()+
                        ") is now in transit",
                NotificationType.TRANSIT
        );
        return shipmentRepository
                .save(shipment);
    }
}