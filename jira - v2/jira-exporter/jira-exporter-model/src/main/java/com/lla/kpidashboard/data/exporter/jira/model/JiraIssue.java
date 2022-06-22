package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssue implements Serializable {

	private static final long serialVersionUID = 3298378578295356149L;

	@JsonProperty("expand")
	private String expand;

	@JsonProperty("id")
	private int id;

	@JsonProperty("self")
	private String self;

	@JsonProperty("key")
	private String key;

	@JsonProperty("fields")
	private JiraFields fields;

	public String getExpand() {
		return expand;
	}

	public void setExpand(final String expand) {
		this.expand = expand;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(final String self) {
		this.self = self;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public JiraFields getFields() {
		return fields;
	}

	public void setFields(final JiraFields fields) {
		this.fields = fields;
	}

}
