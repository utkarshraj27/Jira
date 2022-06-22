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
@Entity(name="epic")
public class Epic {
	@Id
	@Column(name="epic_id")
	private String epicId;
	@Column(name="epic_key")
	private String epicKey;
	@Column(name="epic_status")
	private String epicStatus;
}
