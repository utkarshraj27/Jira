package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraRelease implements Serializable {

	private static final long serialVersionUID = 1051871794751825883L;
	
	@JsonProperty("self")
	private String self;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("archived")
	private boolean archived;
	
	@JsonProperty("released")
	private boolean released;
	
	@JsonProperty("releaseDate")
	private String releaseDate; 
	
	@JsonProperty("userReleaseDate")
	private String userReleaseDate;
	
	@JsonProperty("projectId")
	private long projectId;

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

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getUserReleaseDate() {
		return userReleaseDate;
	}

	public void setUserReleaseDate(String userReleaseDate) {
		this.userReleaseDate = userReleaseDate;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	} 

}
