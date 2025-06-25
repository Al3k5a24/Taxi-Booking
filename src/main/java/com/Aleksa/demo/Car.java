package com.Aleksa.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "driver_id")
	private Driver driver;
	
	@Column
	private String model;
	
	@Column
	private String plateNumbers;

	public Car() {
		super();
	}
	
	public Car(int id, Driver driver, String model, String plateNumbers) {
		super();
		this.id = id;
		this.driver = driver;
		this.model = model;
		this.plateNumbers = plateNumbers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Driver getDriver_id() {
		return driver;
	}

	public void setDriver_id(Driver driver) {
		this.driver = driver;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlateNumbers() {
		return plateNumbers;
	}

	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", driver_id=" + driver + ", model=" + model + ", plateNumbers=" + plateNumbers
				+ "]";
	}
	
	
	
}
