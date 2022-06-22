package com.lla.kpidashboard.data.exporter.jira.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.lla.kpidashboard.data.exporter.jira.repository.EpicRepository;
import com.lla.kpidashboard.data.exporter.jira.repository.ProjectRepository;
import com.lla.kpidashboard.data.exporter.jira.service.JiraApiServices;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectDataLoadScheduler implements CommandLineRunner {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EpicRepository epicRepository;

	@Autowired
	private JiraApiServices apiServices;

	@Value("${jira.project}")
	private String projectName;

	@Value("${jira.loadData}")
	private boolean jiraLoadData;

	/**
	 * This method is used to run the data load of jira on the start of the application
	 * if jira.loadData is true in the properties file
	 *
	 * @param args the args
	 * @throws Exception the exception
	 */
 @Override
	public void run(final String... args) throws Exception {
		log.info("Project Data Load now Started {}", jiraLoadData);
		if (jiraLoadData) {
			log.info("***************************");
			epicRepository.findAll().forEach(epic -> {
				final String jql = "?jql=project = \"Project Evaluation\" AND issuetype in (Epic, Task, Story) AND \"Epic Link\" = "+epic.getEpicId();
				
				
				log.info("Jql Query executed...."+jql);
				//apiServices.saveStory(jql,epic);
			});
		}
	}
}
