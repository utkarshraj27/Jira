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
@Entity(name="task")
@Table
@Builder
public class Task {
	@Id
	@Column(name="task_id")
	private int id;
	@Column(name="task_key")
	private String taskKey;
	@Column(name="status")
	private String status;
	@Column(name="summary")
	private String summary;
	@Column(name="task_start_date")
	private String taskStartDate;
	
	@Column(name="task_end_date")
	private String taskEndDate;
	@Column(name="aggregate_time_spent")
	private long aggregateTimeSpent;
	
	@Column(name="created")
	private String created;
	
	
	@JoinColumn(name = "story_id")
	private int story_id;

}
