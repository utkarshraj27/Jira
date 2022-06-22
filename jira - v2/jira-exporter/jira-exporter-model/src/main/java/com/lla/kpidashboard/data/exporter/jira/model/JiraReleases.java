package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraReleases implements Serializable {

	private static final long serialVersionUID = 3642595788376987812L;

	@JsonProperty("self")
	private String self;
	
	@JsonProperty("maxResults")
	private int maxResults; 
	
	@JsonProperty("startAt")
	private int startAt; 
	
	@JsonProperty("total")
	private int total; 
	
	@JsonProperty("isLast")
	private boolean last; 
	
	@JsonProperty("values")
	private List<JiraRelease> values;

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public int getStartAt() {
		return startAt;
	}

	public void setStartAt(int startAt) {
		this.startAt = startAt;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public List<JiraRelease> getValues() {
		return values;
	}

	public void setValues(List<JiraRelease> values) {
		this.values = values;
	} 
	
}
