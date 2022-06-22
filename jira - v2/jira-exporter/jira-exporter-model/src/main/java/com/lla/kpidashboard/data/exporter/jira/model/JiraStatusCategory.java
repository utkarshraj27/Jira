package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraStatusCategory implements Serializable{

	private static final long serialVersionUID = -4998161164213642780L;
	
	@JsonProperty("self")
	private String self; 
	
	@JsonProperty("id")
	private long id; 
	
	@JsonProperty("key")
	private String key; 
	
	@JsonProperty("colorName")
	private String colorName; 
	
	@JsonProperty("name")
	private String name;

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 

}
