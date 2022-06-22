package com.lla.kpidashboard.data.exporter.jira.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="component")
@Table
@Builder
public class Component {
	@Id
	private String id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "feature_id")
	private Feature feature;
}
