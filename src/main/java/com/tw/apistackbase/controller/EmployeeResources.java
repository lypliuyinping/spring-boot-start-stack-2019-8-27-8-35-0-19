package com.tw.apistackbase.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@RestController
@RequestMapping("/employees")
public class EmployeeResources {
	List<Employee> employees=new ArrayList<Employee>();
	 @GetMapping(path = "/")
	 @ResponseStatus(HttpStatus.OK)
	  public List<Employee> getEmployees() {		 	
		 	employees.add(new Employee(1,"lisi",23,"男"));
		 	employees.add(new Employee(2,"jhh",21,"男"));
	       return employees;
	    }
	 @PostMapping(path = "/add")
	 @ResponseStatus(HttpStatus.CREATED)
	public Employee createEmpolyees(@RequestBody Employee employee){	
		 employees.add(employee);		 
		 return  employee;
	 }
	 
	@PostMapping(path = "/{id}") 
	public ResponseEntity<Employee> getOneEmployee(@PathVariable int id ){
		for (Employee employee:employees) {
			if (employee.getId() == id) {
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);		
	}
	
	@PutMapping(path = "/{id}") 
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee updateEmployee){
		for (Employee employee:employees) {
			if (employee.getId() == updateEmployee.getId()) {
				employee.setAge(updateEmployee.getAge());
				employee.setId(updateEmployee.getId());
				employee.setGender(updateEmployee.getGender());
				employee.setName(updateEmployee.getName());
				return ResponseEntity.ok(employee);
			}
		}		
		return new ResponseEntity(HttpStatus.NOT_FOUND);		
	}

	@DeleteMapping(path = "/{id}") 
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int id){
		for (Employee employee:employees) {
			if (employee.getId() == id) {
				employees.remove(employee);				
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
		
	}
}

