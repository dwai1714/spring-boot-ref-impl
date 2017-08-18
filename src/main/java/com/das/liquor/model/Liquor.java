package com.das.liquor.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Liquor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)


	Long id;
	@Value("${audit.serverUrl}")
	private String serverUrl;
	String name;
	String description;
	String type;
	Integer alcoholContent;
	Integer yearMfg;
	Integer numberOfBlends;
	Integer yearCameToIndia;
	private String createdUser;
	private String lastModifiedBy;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastModifiedDate;
	
	public Liquor() {
	}

	public Liquor(Long id, String name, String description, String type, Integer alcoholContent, Integer yearMfg,
			Integer numberOfBlends, Integer yearCameToIndia) {

		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.alcoholContent = alcoholContent;
		this.yearMfg = yearMfg;
		this.numberOfBlends = numberOfBlends;
		this.yearCameToIndia = yearCameToIndia;
	}

	public Liquor(Long id, String name, String description, String type, Integer alcoholContent, Integer yearMfg,
			Integer numberOfBlends, Integer yearCameToIndia, String createdBy) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.alcoholContent = alcoholContent;
		this.yearMfg = yearMfg;
		this.numberOfBlends = numberOfBlends;
		this.yearCameToIndia = yearCameToIndia;
		this.createdUser = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getAlcoholContent() {
		return alcoholContent;
	}

	public void setAlcoholContent(Integer alcoholContent) {
		this.alcoholContent = alcoholContent;
	}

	public Integer getYearMfg() {
		return yearMfg;
	}

	public void setYearMfg(Integer yearMfg) {
		this.yearMfg = yearMfg;
	}

	public Integer getNumberOfBlends() {
		return numberOfBlends;
	}

	public void setNumberOfBlends(Integer numberOfBlends) {
		this.numberOfBlends = numberOfBlends;
	}

	public Integer getYearCameToIndia() {
		return yearCameToIndia;
	}

	public void setYearCameToIndia(Integer yearCameToIndia) {
		this.yearCameToIndia = yearCameToIndia;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdBy) {
		this.createdUser = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
