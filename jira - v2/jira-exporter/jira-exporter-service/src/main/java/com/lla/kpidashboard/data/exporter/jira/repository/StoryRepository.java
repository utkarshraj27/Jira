package com.lla.kpidashboard.data.exporter.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lla.kpidashboard.data.exporter.jira.domain.Project;
import com.lla.kpidashboard.data.exporter.jira.domain.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

}
