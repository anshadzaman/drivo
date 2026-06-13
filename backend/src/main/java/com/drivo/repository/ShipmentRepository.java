package com.drivo.repository;

import com.drivo.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.drivo.enums.ShipmentStatus;
import java.util.List;

@Repository
public interface ShipmentRepository
        extends JpaRepository<Shipment, Long> {
    List<Shipment> findByShopId(Long shopId);
    long countByStatus(
            ShipmentStatus status);
    List<Shipment>
    findByAssignedDriverId(
            Long driverId);
    long countByShopId(
            Long shopId);

    long countByShopIdAndStatus(
            Long shopId,
            ShipmentStatus status);
    long countByAssignedDriverId (
            long driverId
    );
    long countByAssignedDriverIdAndStatus(
            Long driverId,
            ShipmentStatus status

    );
    long countByStatusIn(
            List<ShipmentStatus> statuses
    );

    long countByShopIdAndStatusIn(
            Long shopId,
            List<ShipmentStatus> statuses
    );

    long countByAssignedDriverIdAndStatusIn(
            Long driverId,
            List<ShipmentStatus> statuses
    );
}