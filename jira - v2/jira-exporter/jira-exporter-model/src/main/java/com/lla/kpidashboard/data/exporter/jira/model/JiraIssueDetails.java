package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssueDetails implements Serializable {

	private static final long serialVersionUID = 3385959573531672518L;

	@JsonProperty("expand")
	private String expand; 
	
	@JsonProperty("startAt")
	private long startAt; 
	
	@JsonProperty("maxResults")
	private long maxResults; 
	
	@JsonProperty("total")
	private long total; 
	
	private List<JiraIssue> issues;

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public long getStartAt() {
		return startAt;
	}

	public void setStartAt(long startAt) {
		this.startAt = startAt;
	}

	public long getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<JiraIssue> getIssues() {
		return issues;
	}

	public void setIssues(List<JiraIssue> issues) {
		this.issues = issues;
	} 
}
