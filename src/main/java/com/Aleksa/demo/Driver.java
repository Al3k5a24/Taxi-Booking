package com.Aleksa.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Username can't be empty!")
	@NotBlank(message="Username can't be blank!")
	@Size(min=2,max=30,message="Username must be betweem 2 and 30 characters!")
	@Column(length=30,name="username")
	private String username;
	
	@NotEmpty(message="Full Name can't be empty!")
	@NotBlank(message="Full Name can't be blank!")
	@Size(min=2,max=30,message="Full Name must be betweem 2 and 30 characters!")
	@Column(length=30,name="full_name")
	private String fullName;
	
	@NotEmpty(message="E-mail can't be empty!")
	@NotBlank(message="E-mail can't be blank!")
	@Size(min=10,max=60,message="E-mail must be betweem 10 and 60 characters!")
	@Column(length=60,name="Email")
	private String email;
	
	@NotNull(message="Phone number can't be empty!")
	@Min(value=1000000,message="Phone must have at least 7 digits")
	@Column(length=15)
	private Long phone;


	@NotNull(message="Licence number can't be empty!")
	@Min(value=1000000,message="Licence must have at least 7 digits")
	@Column(length=15)
	private Long licence;
	
	@NotEmpty(message = "Password can't be empty!")
    @NotBlank(message = "Password can't be blank!")
    @Size(min = 8, message = "Password must be at least 8 characters!")
    @Column(length = 60)
	private String password;
	
	public Driver() {
		super();
	}
	
	public Driver(Integer id,String username, String fullName, String email, Long phone, Long licence, String password) {
		super();
		this.id=id;
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.licence = licence;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getLicence() {
		return licence;
	}

	public void setLicence(long licence) {
		this.licence = licence;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Driver [username=" + username + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone
				+ ", licence=" + licence + ", password=" + password + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
