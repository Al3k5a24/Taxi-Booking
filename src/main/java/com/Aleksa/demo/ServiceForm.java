package com.Aleksa.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Service")
public class ServiceForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String image;
	private String title;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "ServiceForm [id=" + id + ", image=" + image + ", title=" + title + ", description=" + description + "]";
	}
	
	public ServiceForm(int id, String image, String title, String description) {
		super();
		this.id = id;
		this.image = image;
		this.title = title;
		this.description = description;
	}
	
	public ServiceForm() {
		super();
	}
	
	
}
