package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraStatus implements Serializable {

	private static final long serialVersionUID = 937455723914329531L;
	
	@JsonProperty("self")
	private String self; 

	@JsonProperty("description")
	private String description;
	
	@JsonProperty("iconUrl")
	private String iconUrl; 
	
	@JsonProperty("name")
	private String name; 
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("statusCategory")
	private JiraStatusCategory statusCategory;

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JiraStatusCategory getStatusCategory() {
		return statusCategory;
	}

	public void setStatusCategory(JiraStatusCategory statusCategory) {
		this.statusCategory = statusCategory;
	} 
	
}
