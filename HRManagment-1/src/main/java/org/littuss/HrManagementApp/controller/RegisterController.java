package org.littuss.HrManagementApp.controller;

import org.littuss.HrManagementApp.LoginVariables.LoginRequest;
import org.littuss.HrManagementApp.model.EmployeeRegisterEntity;
import org.littuss.HrManagementApp.service.EmployeeRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users") //main
@CrossOrigin("*")
public class RegisterController {

	 @Autowired
	 private EmployeeRegisterService empRegSer;
	 
	 //register
	 @GetMapping("/register/{email}")
	 public EmployeeRegisterEntity getUserByEmail(@PathVariable String email) {
	        return empRegSer.findByEmail(email);
	    }
	 
	 @PostMapping("/register")
	    public EmployeeRegisterEntity createUser(@RequestBody EmployeeRegisterEntity EmpregEnt) {
		 System.out.println("Received request: " + EmpregEnt.toString());   
		 return empRegSer.save(EmpregEnt);
	    }
	 //login
	 @PostMapping("/login")
	    public String loginUser(@RequestBody LoginRequest loginRequest) {
	        String email = loginRequest.getEmail();
	        String password = loginRequest.getPassword();

	        // Perform login validation
	        EmployeeRegisterEntity user = empRegSer.findByEmailAndPassword(email, password);

	        if (user != null) {
	            // Successful login
	            return "Login successful";
	        } else {
	            // Failed login
	            return "Login failed";
	        }
	    }
}
