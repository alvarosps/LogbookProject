package com.example.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Car;
import com.example.model.Maintenance;
import com.example.model.User;
import com.example.repository.CarRepository;
import com.example.repository.UserRepository;

@Service("carService")
public class CarServiceImpl implements CarService{	

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CarRepository carRepository;
	@Override
	public Car findCarByName(String name) {
		return carRepository.findByName(name);
	}
	@Override
	public void saveCar(Car car, String email) {
		
		User user = userRepository.findByEmail(email);
		
		car.setUser(user);
		car.setMaintenances(new HashSet<Maintenance>());
		
		carRepository.save(car);
	}
	public void saveCar(Car car) {
		
		
		
		carRepository.save(car);
	}
	
	
	
}
