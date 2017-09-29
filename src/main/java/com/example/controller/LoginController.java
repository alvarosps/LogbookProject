package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Car;
import com.example.model.Maintenance;
import com.example.model.User;
import com.example.repository.CarRepository;
import com.example.repository.MaintenanceRepository;
import com.example.service.CarService;
import com.example.service.MaintenanceService;
import com.example.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private CarService carService;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@Autowired
	private MaintenanceRepository maintenanceRepository;
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Bem vindo, " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		
		modelAndView.addObject("cars", carRepository.findAll());
		
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	
	@RequestMapping(value="/carRegistration", method = RequestMethod.GET)
	public ModelAndView carRegistration(){
		ModelAndView modelAndView = new ModelAndView();
		Car car = new Car();
		modelAndView.addObject("car", car);
		modelAndView.setViewName("carRegistration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/carRegistration", method = RequestMethod.POST)
	public ModelAndView createNewCar(@Valid Car car, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		Car carExists = carService.findCarByName(car.getName());
		if(carExists != null) {
			bindingResult
			.rejectValue("name", "error.car",
					"Ja existe um carro com esse nome no banco de dados.");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("carRegistration");
		} else {
			carService.saveCar(car);
			modelAndView.addObject("successMessage", "Carro Registrado com sucesso");
			modelAndView.addObject("car", new Car());
			modelAndView.setViewName("carRegistration");
			
		}
		return modelAndView;
		
	}
	
	@RequestMapping(value="/admin/maintenances", method = RequestMethod.GET)
	public ModelAndView maintenances() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		modelAndView.addObject("maintenances", maintenanceRepository.findAll());
		
		modelAndView.setViewName("admin/maintenances");
		return modelAndView;
	}
	
	@RequestMapping(value="/maintenanceRegistration", method = RequestMethod.GET)
	public ModelAndView maintenanceRegistration(){
		ModelAndView modelAndView = new ModelAndView();
		Maintenance maintenance = new Maintenance();
		modelAndView.addObject("maintenance", maintenance);
		modelAndView.setViewName("maintenanceRegistration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/maintenanceRegistration", method = RequestMethod.POST)
	public ModelAndView createNewCar(@Valid Maintenance maintenance, BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("carRegistration");
		} else {
			maintenanceService.saveMaintenance(maintenance);
			modelAndView.addObject("successMessage", "Carro Registrado com sucesso");
			modelAndView.addObject("maintenance", new Maintenance());
			modelAndView.setViewName("maintenanceRegistration");
			
		}
		return modelAndView;
		
	}
	
}
