package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JiraIssueFields implements Serializable {

	private static final long serialVersionUID = -4571167535780941004L;

	@JsonProperty("status")
	private JiraStatus status;
	
	@JsonProperty("summary")
	private String summary;
	
	@JsonProperty("priority")
	private JiraPriority priority;
	
	@JsonProperty("issuetype")
	private JiraIssuetype issuetype;

	public JiraStatus getStatus() {
		return status;
	}

	public void setStatus(JiraStatus status) {
		this.status = status;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public JiraPriority getPriority() {
		return priority;
	}

	public void setPriority(JiraPriority priority) {
		this.priority = priority;
	}

	public JiraIssuetype getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(JiraIssuetype issuetype) {
		this.issuetype = issuetype;
	}

	
}
