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
@Entity(name = "feature")
@Table
@Builder
public class Feature {
	@Id
	private int id;
	@Column(name="feature_key")
	private String key;
	private String status;
	@Column(name = "task_start_date")
	private LocalDateTime taskStartDate;
	@Column(name = "release_date_prod")
	private LocalDateTime releaseDateProd;
	@Column(name = "release_date_qa")
	private LocalDateTime releaseDateQA;

	private String projectId;
	private long effortProd;
	private long effortQA;
	private Integer mttdQa;
	private Integer mttdProd;
	private String mttdQaMonth;
	private String mttdProdMonth;

	private String releaseLabelQA;
	private String releaseLabelProd;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedTime;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "feature_id")
	private List<SubTask> subTasks;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "feature_id")
	private List<Component> components;
}
