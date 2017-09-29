package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "maintenances")
public class Maintenance {

	private String carName;
	private int maintenanceId;
	private String name;
	private String km;
	private String description;
	private Car car;
	
	public Maintenance() {
		
	}
	
	public Maintenance(String name, String km, String description) {
		this.name = name;
		this.km = km;
		this.description = description;
	}
	
	@Column(name = "maintenance_car_name")
	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(int maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	@Column(name = "maintenance_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "maintenance_km")
	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	@Column(name = "maintenance_description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "cars_car_id")
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
}
