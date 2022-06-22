package com.lla.kpidashboard.data.exporter.jira.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="project")
public class Project {
	@Id
	private String id;
	@Column(name="project_name")
	private String projectName;
	private boolean active;
}
