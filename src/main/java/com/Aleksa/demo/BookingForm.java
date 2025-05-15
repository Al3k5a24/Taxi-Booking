package com.Aleksa.demo;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="BookingForm")
public class bookingForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@NotEmpty(message="Name can't be empty!")
	@NotBlank(message="Name can't be blank!")
	@Size(min=2,max=30,message="Name must be betweem 2 and 30 characters!")
	@Pattern(regexp="^[A-Za-z]+$",message="Name must contain only letters!")
	@Column(length=30,name="Name")
	private String name;
	
	@NotEmpty(message="E-mail can't be empty!")
	@NotBlank(message="E-mail can't be blank!")
	@Size(min=10,max=60,message="E-mail must be betweem 10 and 60 characters!")
	@Column(length=60,name="Email")
	private String email;
	
	@NotEmpty(message="Location can't be empty!")
	@NotBlank(message="Location can't be blank!")
	@Size(min=2,max=50,message="Location must be betweem 2 and 50 characters!")
	@Column(length=50,name="Location")
	private String from;
	
	@NotEmpty(message="Destination can't be empty!")
	@NotBlank(message="Destination can't be blank!")
	@Size(min=2,max=100,message="Destination must be betweem 2 and 100 characters!")
	@Column(length=100,name="Destination")
	private String to;
	
	@NotNull(message="Time can't be empty!")
	@Column(length=500,name="Time")
	private LocalTime time;
	
	@NotNull(message="Date can't be empty!")
	@Column(length=500,name="Date")
	private String date;
	
	@NotEmpty(message="Comfort can't be empty!")
	@Size(min=5,max=50,message="Comfort must be betweem 5 and 50 characters!")
	@Column(length=50,name="Comfort")
	private String comfort;
	
	@Min(value=1,message="There must be at least 1 adult!")
	@Max(value=4,message="Maximum is 4 adults per vehicle!")
	@Column(name="Adults")
	private int adult;
	
	@Max(value=3,message="Maximum is 3 children per vehicle!")
	@Column(name="Children")
	private int children;
	
	@NotEmpty(message="Message can't be empty!")
	@NotBlank(message="Destination can't be blank!")
	@Size(min=10,max=2000,message="Message must be betweem 10 and 2000 characters!")
	@Column(length=2000,name="Message")
	private String message;
	
	public bookingForm(int id, String name, String email, String from, String to, LocalTime time, String date,
			String comfort, int adult, int children, String message) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.from = from;
		this.to = to;
		this.time = time;
		this.date = date;
		this.comfort = comfort;
		this.adult = adult;
		this.children = children;
		this.message = message;
	}

	public bookingForm() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComfort() {
		return comfort;
	}

	public void setComfort(String comfort) {
		this.comfort = comfort;
	}

	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "BookingForm [id=" + id + ", name=" + name + ", email=" + email + ", from=" + from + ", to=" + to
				+ ", time=" + time + ", date=" + date + ", comfort=" + comfort + ", adult=" + adult + ", children="
				+ children + ", message=" + message + "]";
	}
	
	
	
	
}
