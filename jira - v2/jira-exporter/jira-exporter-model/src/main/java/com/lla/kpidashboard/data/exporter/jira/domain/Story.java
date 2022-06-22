package com.lla.kpidashboard.data.exporter.jira.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "story")
@Table
@Builder
public class Story {
	@Id
	private int id;
	@Column(name="story_key")
	private String StoryKey;
	
	@Column(name="epic_id")
	private String epicId;
	
	@Column(name = "story_status")
	private String storyStatus;
	
	@Column(name = "deployment_time")
	private String deploymentTime;
	
	@Column(name = "deployment_timeqa")
	private int deploymentTimeQA;
	
	@Column(name = "effort_per_release")
	private long effortPerRelease;
	
	
	

	
	
	
	
}
