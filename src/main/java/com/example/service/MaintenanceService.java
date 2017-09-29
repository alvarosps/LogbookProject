package com.example.service;

import com.example.model.Maintenance;

public interface MaintenanceService {

	public Maintenance findMaintenanceByName(String name);
	public void saveMaintenance(Maintenance maintenance, String carName);
	public void saveMaintenance(Maintenance maintenance);
	
}
