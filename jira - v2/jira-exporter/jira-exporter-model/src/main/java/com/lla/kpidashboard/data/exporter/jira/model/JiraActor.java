package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraActor implements Serializable {
	
	private static final long serialVersionUID = 32488651343465119L;
	
	@JsonProperty("self")
	private String self; 
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("key")
	private String key; 
	
	@JsonProperty("emailAddress")
	private String emailAddress;
	
	@JsonProperty("displayName")
	private String displayName;
	
	@JsonProperty("active")
	private boolean active;
	
	@JsonProperty("timeZone")
	private String timeZone; 
	
	@JsonProperty("avatarUrls")
	private JiraAvatarUrls avatarUrls;

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public JiraAvatarUrls getAvatarUrls() {
		return avatarUrls;
	}

	public void setAvatarUrls(JiraAvatarUrls avatarUrls) {
		this.avatarUrls = avatarUrls;
	} 

}
