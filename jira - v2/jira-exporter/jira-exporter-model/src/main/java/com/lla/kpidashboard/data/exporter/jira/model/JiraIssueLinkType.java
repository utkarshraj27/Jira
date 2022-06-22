package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssueLinkType implements Serializable {

	private static final long serialVersionUID = -9001271429415198265L;

	@JsonProperty("id")
	private String id; 
	
	@JsonProperty("name")
	private String name; 
	
	@JsonProperty("inward")
	private String inward; 
	
	@JsonProperty("outward")
	private String outward; 
	
	@JsonProperty("self")
	private String self;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInward() {
		return inward;
	}

	public void setInward(String inward) {
		this.inward = inward;
	}

	public String getOutward() {
		return outward;
	}

	public void setOutward(String outward) {
		this.outward = outward;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}
}
