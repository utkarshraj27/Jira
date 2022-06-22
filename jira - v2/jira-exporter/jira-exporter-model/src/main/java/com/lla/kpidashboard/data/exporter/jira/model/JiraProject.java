package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraProject implements Serializable {

	private static final long serialVersionUID = 3622027212382919760L;
	
	@JsonProperty("self")
	private String self; 
	
	@JsonProperty("id")
	private String id; 
	
	@JsonProperty("key")
	private String key; 
	
	@JsonProperty("name")
	private String name; 
	
	@JsonProperty("avatarUrls")
	private JiraAvatarUrls avatarUrls;

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JiraAvatarUrls getAvatarUrls() {
		return avatarUrls;
	}

	public void setAvatarUrls(JiraAvatarUrls avatarUrls) {
		this.avatarUrls = avatarUrls;
	} 

}
