package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JiraOutwardIssue implements Serializable {

	private static final long serialVersionUID = -6132258604126332518L;

	@JsonProperty("id")
	private String id; 
	
	@JsonProperty("key")
	private String key; 
	
	@JsonProperty("self")
	private String self; 
	
	@JsonProperty("fields")
	private JiraIssueFields fields;

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

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public JiraIssueFields getFields() {
		return fields;
	}

	public void setFields(JiraIssueFields fields) {
		this.fields = fields;
	}
	
	
}
