package com.lla.kpidashboard.data.exporter.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lla.kpidashboard.data.exporter.jira.domain.SubTask;

public interface SubTaskRepository extends JpaRepository<SubTask, Integer> {

}
