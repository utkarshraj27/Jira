package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraAvatarUrls implements Serializable {

	private static final long serialVersionUID = -8930982217579588332L;
	
	@JsonProperty("48x48")
	private String i48x48; 
	
	@JsonProperty("24x24")
	private String i24x24;
	
	@JsonProperty("16x16")
	private String i16x16;
	
	@JsonProperty("32x32")
	private String i32x32;

	public String getI48x48() {
		return i48x48;
	}

	public void setI48x48(String i48x48) {
		this.i48x48 = i48x48;
	}

	public String getI24x24() {
		return i24x24;
	}

	public void setI24x24(String i24x24) {
		this.i24x24 = i24x24;
	}

	public String getI16x16() {
		return i16x16;
	}

	public void setI16x16(String i16x16) {
		this.i16x16 = i16x16;
	}

	public String getI32x32() {
		return i32x32;
	}

	public void setI32x32(String i32x32) {
		this.i32x32 = i32x32;
	}
}
