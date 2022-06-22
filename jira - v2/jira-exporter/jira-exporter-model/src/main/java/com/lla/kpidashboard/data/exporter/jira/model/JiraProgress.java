package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraProgress implements Serializable {

	private static final long serialVersionUID = -2868383067653530885L;
	
	@JsonProperty("progress")
	private long progress; 
	
	@JsonProperty("total")
	private long total;

	public long getProgress() {
		return progress;
	}

	public void setProgress(long progress) {
		this.progress = progress;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	} 

}
