package com.example.service;

import com.example.model.Car;

public interface CarService {

	public Car findCarByName(String name);
	public void saveCar(Car car, String email);
	public void saveCar(Car car);
}
