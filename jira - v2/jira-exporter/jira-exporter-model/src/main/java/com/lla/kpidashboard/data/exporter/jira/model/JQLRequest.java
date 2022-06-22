package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JQLRequest implements Serializable {
	
	private static final long serialVersionUID = 5357861132464292872L;
	
	@JsonProperty("jql")
	private String jql;
	
	@JsonProperty("startAt")
	private String startAt; 
	
	@JsonProperty("maxResults")
	private String maxResults = "500"; 
	
	public String getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(String maxResults) {
		this.maxResults = maxResults;
	}

	public String getStartAt() {
		return startAt;
	}

	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}

	public String getJql() {
		return jql;
	}

	public void setJql(String jql) {
		this.jql = jql;
	} 
}
