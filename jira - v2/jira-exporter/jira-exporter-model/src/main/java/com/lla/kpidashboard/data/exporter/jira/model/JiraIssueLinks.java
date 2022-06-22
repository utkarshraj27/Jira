package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssueLinks implements Serializable {

	private static final long serialVersionUID = -5154729196725293691L;

	@JsonProperty("self")
	private String self; 
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("type")
	private JiraIssueLinkType type;
	
	@JsonProperty("outwardIssue")
	private JiraOutwardIssue outwardIssue;

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

	public JiraIssueLinkType getType() {
		return type;
	}

	public void setType(JiraIssueLinkType type) {
		this.type = type;
	}

	public JiraOutwardIssue getOutwardIssue() {
		return outwardIssue;
	}

	public void setOutwardIssue(JiraOutwardIssue outwardIssue) {
		this.outwardIssue = outwardIssue;
	}
	


			
}
