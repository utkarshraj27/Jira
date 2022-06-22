package com.lla.kpidashboard.data.exporter.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lla.kpidashboard.data.exporter.jira.domain.Epic;
import com.lla.kpidashboard.data.exporter.jira.domain.Project;

@Repository
public interface EpicRepository extends JpaRepository<Epic, String> {

}
