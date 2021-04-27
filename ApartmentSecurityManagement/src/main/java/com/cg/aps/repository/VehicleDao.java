package com.cg.aps.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.aps.entities.VehicleEntity;

/**
 * @author Anshul Joshi
 *
 */
@Repository
public interface VehicleDao extends JpaRepository<VehicleEntity, Long> {
	Optional<VehicleEntity> findByName(String name);

	Optional<VehicleEntity> findByVehicleNo(String id);

	VehicleEntity deleteById(long id);
}
