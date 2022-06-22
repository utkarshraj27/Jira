package com.lla.kpidashboard.data.exporter.jira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lla.kpidashboard.data.exporter.jira.domain.Project;
@Repository

public interface ProjectRepository extends JpaRepository<Project, String> {

	List<Project> findAllByActiveIsTrue();
}
