package com.Aleksa.demo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContactForm {

	private int id;
	@NotEmpty(message="Name can't be empty!")
	@Size(min=2,max=30,message="Name must be between 2 and 30 characters!")
	private String name;
	@NotEmpty(message="E-mail can't be empty!")
	@Size(min=10,max=60,message="Email must be between 10 and 60 characters!")
	private String email;
	@NotNull(message="Phone number can't be empty!")
	@Min(value=1000000000,message="Phone must have at least 10 digits")
	@Max(value=9999999999L,message="Phone must have at least 10 digits")
	private Long phone;
	@NotEmpty(message="Message can't be empty!")
	@Size(min=5,max=500,message="Message must be between 5 and 500 characters!")
	private String message;

	public ContactForm(int id, String name, String email, Long phone, String message) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ContactForm() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ContactForm [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", message="
				+ message + "]";
	}


	
	
}
