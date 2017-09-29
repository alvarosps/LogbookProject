package com.example.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Car;
import com.example.model.Maintenance;
import com.example.repository.CarRepository;
import com.example.repository.MaintenanceRepository;

@Service("maintenanceService")
public class MaintenanceServiceImpl implements MaintenanceService {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private MaintenanceRepository maintenanceRepository;
	
	@Override
	public Maintenance findMaintenanceByName(String name) {
		return maintenanceRepository.findByName(name);
	}

	@Override
	public void saveMaintenance(Maintenance maintenance, String carName) {
		
		Car car = carRepository.findByName(carName);
		
		Set<Maintenance> carMaintenances = car.getMaintenances();
		
		carMaintenances.add(maintenance);
		
		car.setMaintenances(carMaintenances);
		
		maintenance.setCar(car);
		
		maintenanceRepository.save(maintenance);
		
	}

	@Override
	public void saveMaintenance(Maintenance maintenance) {

		maintenanceRepository.save(maintenance);
		
	}

}
