package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JiraIssueSubtasks implements Serializable {

	private static final long serialVersionUID = -2551344295238216994L;

	@JsonProperty("self")
	private String self; 
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("key")
	private String key; 
	
	@JsonProperty("fields")
	private JiraIssueFields fields;

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

	public JiraIssueFields getFields() {
		return fields;
	}

	public void setFields(JiraIssueFields fields) {
		this.fields = fields;
	}

	
}
