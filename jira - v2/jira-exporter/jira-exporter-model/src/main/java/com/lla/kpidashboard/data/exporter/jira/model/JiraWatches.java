package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraWatches implements Serializable {

	private static final long serialVersionUID = -6325274917103835473L;
	
	@JsonProperty("self")
	private String self; 
	
	@JsonProperty("watchCount")
	private long watchCount; 
	
	@JsonProperty("isWatching")
	private boolean watching;

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public long getWatchCount() {
		return watchCount;
	}

	public void setWatchCount(long watchCount) {
		this.watchCount = watchCount;
	}

	public boolean isWatching() {
		return watching;
	}

	public void setWatching(boolean watching) {
		this.watching = watching;
	} 

}
