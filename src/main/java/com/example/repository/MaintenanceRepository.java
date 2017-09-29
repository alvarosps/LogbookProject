package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Maintenance;
@Repository("maintenanceRepository")
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

	Maintenance findByName(String name); 
	
}
