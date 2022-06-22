package com.lla.kpidashboard.data.exporter.jira.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="subtask")
@Table
@Builder
public class SubTask {
	@Id
	private int id;
	@Column(name="subtask_key")
	private String key;
	private String status;
	private String summary;
	private String taskStartDate;
	private String taskEndDate;
	private String created;
	private long aggregateTimeSpent;
	private long duration;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedTime;

	@ManyToOne
	@JoinColumn(name = "feature_id")
	private Feature feature;

}
