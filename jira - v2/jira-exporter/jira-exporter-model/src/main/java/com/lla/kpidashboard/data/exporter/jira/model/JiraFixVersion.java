package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraFixVersion implements Serializable {

	private static final long serialVersionUID = 5255643308621383561L;
	
	@JsonProperty("self")
	private String self; 
	
	@JsonProperty("id")
	private String id; 
	
	@JsonProperty("description")
	private String description; 

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("archived")
	private boolean archived; 
	
	@JsonProperty("released")
	private boolean released;

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public boolean isReleased() {
		return released;
	}

	public void setReleased(boolean released) {
		this.released = released;
	}
}
