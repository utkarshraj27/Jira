package com.lla.kpidashboard.data.exporter.jira.service;

import static com.lla.kpidashboard.data.exporter.jira.utility.Utility.calculateMTD;
import static com.lla.kpidashboard.data.exporter.jira.utility.Utility.getBasicAuthHeader;
import static com.lla.kpidashboard.data.exporter.jira.utility.Utility.getJiraSearchUrl;
import static com.lla.kpidashboard.data.exporter.jira.utility.Utility.monthTrend;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lla.kpidashboard.data.exporter.jira.domain.Component;
import com.lla.kpidashboard.data.exporter.jira.domain.Epic;
import com.lla.kpidashboard.data.exporter.jira.domain.Feature;
import com.lla.kpidashboard.data.exporter.jira.domain.Feature.FeatureBuilder;
import com.lla.kpidashboard.data.exporter.jira.domain.Story;
import com.lla.kpidashboard.data.exporter.jira.domain.Task;
import com.lla.kpidashboard.data.exporter.jira.domain.SubTask;
import com.lla.kpidashboard.data.exporter.jira.domain.Task.TaskBuilder;
import com.lla.kpidashboard.data.exporter.jira.lambda.LambdaFunction;
import com.lla.kpidashboard.data.exporter.jira.domain.Story.StoryBuilder;
import com.lla.kpidashboard.data.exporter.jira.model.JiraComponent;
import com.lla.kpidashboard.data.exporter.jira.model.JiraFields;
import com.lla.kpidashboard.data.exporter.jira.model.JiraFixVersion;
import com.lla.kpidashboard.data.exporter.jira.model.JiraIssue;
import com.lla.kpidashboard.data.exporter.jira.model.JiraIssueDetails;
import com.lla.kpidashboard.data.exporter.jira.model.JiraOutwardIssue;
import com.lla.kpidashboard.data.exporter.jira.repository.FeatureRepository;
import com.lla.kpidashboard.data.exporter.jira.repository.StoryRepository;
import com.lla.kpidashboard.data.exporter.jira.repository.TaskRepository;
import com.lla.kpidashboard.data.exporter.jira.utility.Utility;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tguharay
 * This class is used to load the data from jira to database
 */

@Service
@Slf4j
public class JiraApiServices {

	private static final String TASK_END_QA = "t25-regression testing";

	private static final String TASK_END_PROD = "t40-release cycle completed";

	private static final String TASK_START = "t1-feature created";

	

	private RestTemplate restSecureTemplate = new RestTemplate();

	@Autowired
	private FeatureRepository featureRepository;
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private TaskRepository taskRepository;

	@Value("${jira.host:localhost}")
	private String host = System.getenv("jira_hostname");

	@Value("${jira.port:8080}")
	private String port = System.getenv("jira_port");

	@Value("${jira.issecure:true}")
	private boolean insecure = true;

	@Value("${jira.user:administrator}")
	private String jiraUser = System.getenv("jira_username");

	@Value("${jira.password:admin}")
	private String jiraPassword = System.getenv("jira_password");

	@Value("${jira.project:ESDN}")
	private String jiraProject;

	private RestTemplate getRestTemplateBasedOnSecurity() {
		return restSecureTemplate;
		}

	private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			.append(DateTimeFormatter.ISO_LOCAL_DATE_TIME).optionalStart().appendOffset("+HH:MM", "+00:00")
			.optionalEnd().optionalStart().appendOffset("+HHMM", "0000").optionalEnd().toFormatter();

	/**
	 * This method is used to get the project id and jql to fetch the data from jira and then
	 * insert the data to database
	 * <p>
	 * @param projectId :: project link id of jira feature
	 * @param jql : jql to filter the data
	 */
	/*
	public void saveFeatureList(final String projectId, final String jql) {
		log.info("JQL :: {}", jql);
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", getBasicAuthHeader(jiraUser, jiraPassword));
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		final String jiraUrl = getJiraSearchUrl(insecure, host, port) + jql;
		System.out.println(jiraUrl);

		final ResponseEntity<JiraIssueDetails> response = restSecureTemplate.getForEntity(jiraUrl,
				JiraIssueDetails.class);
		final JiraIssueDetails jiraIssueDetails = response.getBody();
		if (jiraIssueDetails != null) {
			jiraIssueDetails.getIssues().stream().forEach(issue -> {
				log.info("Task Key Name {}", issue.getKey());

				final List<SubTask> subTasks = issue.getFields().getSubtasks().parallelStream()
						.map(subTask -> getSubTask(subTask.getSelf())).collect(Collectors.toList());

				FeatureBuilder featureBuilder = Feature.builder();
				final String status = issue.getFields().getStatus().getName();
				featureBuilder = calculateTaskStartDateEndDate(subTasks, status, featureBuilder);

				featureBuilder = calculateReleaseLabel(issue.getFields().getFixVersions().stream()
						.map(JiraFixVersion::getName).collect(Collectors.toList()), featureBuilder);

				long effortQA = calculateEffort(subTasks, TASK_END_QA, status.toUpperCase(), Utility.getQAStages());
				long effortProd = calculateEffort(subTasks, TASK_END_PROD, status.toUpperCase(),
						Utility.getProdStages());

				featureBuilder = featureBuilder.components(getComponents(issue.getFields().getComponents()));

				final Feature feature = featureBuilder.id(issue.getId()).key(issue.getKey())
						.status(status.toUpperCase()).projectId(projectId).effortQA(effortQA).effortProd(effortProd)
						.build();

				subTasks.forEach(st -> st.setFeature(feature));
				feature.setSubTasks(subTasks);

				featureRepository.save(feature);
			});
		}
	}
	
	*/
	public void saveStory(final String jql,Epic epic, Session session) {
		log.info("JQL :: {}", jql);
		final String jiraUrl = getJiraSearchUrl(insecure, host, port) + jql;
		System.out.println(jiraUrl);
		log.info("**********************");
		final ResponseEntity<JiraIssueDetails> response = getRestTemplateBasedOnSecurity().exchange(jiraUrl, HttpMethod.GET,
				getRequest(), JiraIssueDetails.class);
		System.out.println(response);
		
		final JiraIssueDetails jiraIssueDetails = response.getBody();
		System.out.println(jiraIssueDetails);
		if (jiraIssueDetails != null) {
			jiraIssueDetails.getIssues().stream().forEach(issue -> {
				log.info("Task Key Name {}",issue.getKey());
				List<Task> allTasks = new ArrayList<Task>();
				
				issue.getFields().getIssuelinks().stream().forEach(issuelink -> {
					/*TaskBuilder taskBuilder = Task.builder();
					final JiraOutwardIssue outWardIssue = issuelink.getOutwardIssue();
					final Task task = taskBuilder.id(Integer.parseInt(outWardIssue.getId())).
					taskKey(outWardIssue.getKey()).status(outWardIssue.getFields().getStatus().getName()).
					summary(outWardIssue.getFields().getSummary()).story_id(issue.getId()).build();
					
					taskRepository.save(task);*/
				
					final ResponseEntity<JiraIssue> taskResponse = getRestTemplateBasedOnSecurity().exchange(issuelink.getOutwardIssue().getSelf(), HttpMethod.GET,
							getRequest(), JiraIssue.class);
					
					final JiraIssue taskIssue = taskResponse.getBody();
					if (taskIssue != null) {
						final JiraFields fields = taskIssue.getFields();
						TaskBuilder taskBuilder = Task.builder();
						
						Task task = taskBuilder.id(taskIssue.getId()).taskKey(taskIssue.getKey()).story_id(issue.getId()).
							status(fields.getStatus().getName()).summary(fields.getSummary()).
							aggregateTimeSpent(fields.getAggregatetimespent()).created(fields.getCreated()).taskStartDate(fields.getTaskStartDate()).
						    taskEndDate(fields.getTaskEndDate()).build();	
						
						allTasks.add(task);
					//	taskRepository.save(task);
						session.saveOrUpdate(task);
					}
					
			});
				StoryBuilder storyBuilder = Story.builder();
				final String status = issue.getFields().getStatus().getName();
				long effortPerRelease = calculateEffort(allTasks,status.toUpperCase(),Utility.getTaskstages());
				String deploymentTime = issue.getFields().getCreated();
				LocalDateTime createDateTime = LocalDateTime.parse(deploymentTime, formatter);
				DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				String formattedDate = createDateTime.format(myFormatObj);
				
				
				
				
				final Story story = storyBuilder.id(issue.getId()).StoryKey(issue.getKey()).storyStatus
						(status).epicId(epic.getEpicId()).deploymentTime(formattedDate).
						effortPerRelease(effortPerRelease).build();
				//storyRepository.save(story);
				session.saveOrUpdate(story);
				
		
			});	
	}
	}
	

	/**
	 * This method is used to get the data of a subtask
	 * <p>
	 * @param subTaskUrl : url of the subtask
	 * @return : return detail of subtask
	 */
	
	
	/*private Task getTask(final String taskUrl,final int storyId) {
		final ResponseEntity<JiraIssue> taskResponse = getrestTemplateBasedonSecurity().exchange(taskUrl, HttpMethod.GET,
				getRequest(), JiraIssue.class);
		final JiraIssue taskIssue = taskResponse.getBody();
		if (taskIssue != null) {
			final JiraFields fields = taskIssue.getFields();
			TaskBuilder taskBuilder = Task.builder();
			Task task = taskBuilder.id(taskIssue.getId()).taskKey(taskIssue.getKey()).status
					(fields.getStatus().getName()).summary(fields.getSummary()).taskStartDate(fields.getTaskStartDate())
					.taskEndDate(fields.getTaskEndDate()).aggregateTimeSpent(fields.getAggregatetimespent()).story_id(storyId)
					.build();
					
		}
		return task;
	}*/
	
	
	/*
	private Task getSubTask(final String subTaskUrl,final int storyId) {
		final ResponseEntity<JiraIssue> subTaskResponse = getrestTemplateBasedonSecurity().getForEntity(subTaskUrl,
				JiraIssue.class);
		final JiraIssue subTaskIssue = subTaskResponse.getBody();
		if (subTaskIssue != null) {
			final JiraFields fields = subTaskIssue.getFields();
			return Task.builder().id(subTaskIssue.getId()).key(subTaskIssue.getKey())
					.status(fields.getStatus().getName()).summary(fields.getSummary())
					.taskStartDate(fields.getTaskStartDate()).taskEndDate(fields.getTaskEndDate())
					.aggregateTimeSpent(fields.getAggregatetimespent()).created(fields.getCreated()).build();
		}
		return new SubTask();
	}
	*/

	/**
	 * This method is used to fetch the data of the components
	 * <p>
	 * @param jiraComponents : List of JiraComponents
	 * @return : List of Components
	 */
	private List<Component> getComponents(final List<JiraComponent> jiraComponents) {
		if (jiraComponents == null || jiraComponents.isEmpty()) {
			return Collections.emptyList();
		}
		return jiraComponents.stream().map(
				jiraComponent -> Component.builder().id(jiraComponent.getId()).name(jiraComponent.getName()).build())
				.collect(Collectors.toList());
	}

	/**
	 * This method is used to calculate the task start date, task end date of QA and release date to Prod from list of subtask
	 * To Calculate the start date use start date of job T1
	 * To Calculate the end date of QA use end date of job T25
	 * To Calculate the end date of Prod use end date of job T40
	 * <p>
	 * @param subTasks : List of subtasks
	 * @param startTask : start task name
	 * @param endTaskProd : production end task name 
	 * @param endTaskQA : QA end task name
	 * @param status : list of avaiable status to validate task end
	 * @return : return specific dates for task start and end dates.
	 */
	private Map<String, LocalDateTime> calculateTaskDates(final List<SubTask> subTasks, final String startTask,
			final String endTaskProd, final String endTaskQA, final String status) {
		final Map<String, LocalDateTime> taskDates = new HashMap<>();
		if (subTasks == null || subTasks.isEmpty()) {
			return Collections.emptyMap();
		}

		for (final SubTask subTask : subTasks) {
			if (!StringUtils.isBlank(subTask.getSummary()) && subTask.getSummary().toLowerCase().contains(startTask)
					&& StringUtils.isNotBlank(subTask.getTaskStartDate())) {
				taskDates.put(startTask, OffsetDateTime.parse(subTask.getTaskStartDate(), formatter).toLocalDateTime());
			}
			if (!StringUtils.isBlank(subTask.getSummary()) && subTask.getSummary().toLowerCase().contains(endTaskProd)
					&& StringUtils.isNotBlank(subTask.getTaskEndDate()) && Utility.getProdStages().contains(status)) {
				taskDates.put(endTaskProd, OffsetDateTime.parse(subTask.getTaskEndDate(), formatter).toLocalDateTime());
			}
			if (!StringUtils.isBlank(subTask.getSummary()) && subTask.getSummary().toLowerCase().contains(endTaskQA)
					&& StringUtils.isNotBlank(subTask.getTaskEndDate()) && Utility.getQAStages().contains(status)) {
				taskDates.put(endTaskQA, OffsetDateTime.parse(subTask.getTaskEndDate(), formatter).toLocalDateTime());
			}
		}
		return taskDates;
	}

	
	/**
	 * Calculate effort for a specific feature. This method calculate required effort for QA and Production completion.
	 * This method first check the task creation date of end task. Then if any subtask creation date less than task creation date of end task
	 * then is sum up the effort of the task.
	 *<p>
	 * @param subTasks the sub tasks : List of subtasks
	 * @param endTask the end task : name of the end task
	 * @param status the status : final status of the feature
	 * @param stages the stages : available status for completion of end task
	 * @return the long : calculated effort
	 */
	private long calculateEffort(final List<Task> tasks, final String status,
			List<String> stages) {

		long effort = 0;
		boolean completeStageFlag = false;
		String createDate = null;
		for (final Task task : tasks) {
			if (StringUtils.isNotBlank(task.getSummary())
					 && stages.contains(status)) {
				createDate = task.getCreated();
				completeStageFlag = true;
				break;
			}
		}

		if (completeStageFlag) {
			//LocalDateTime createDateTime = LocalDateTime.parse(createDate, formatter);
			for (final Task task : tasks) {
			//	LocalDateTime subTaskCreateDate = LocalDateTime.parse(task.getCreated(), formatter);
			//	if (createDateTime.compareTo(subTaskCreateDate) >= 0) {
					effort += task.getAggregateTimeSpent();
				}
			}
		//}

		return completeStageFlag ? effort : 0;
	}

	/**
	 * Calculate release label for QA and Prod for a feature
	 *
	 * @param fixVersions the fix versions
	 * @param featureBuilder the feature builder
	 * @return the feature builder
	 */
	private FeatureBuilder calculateReleaseLabel(final List<String> fixVersions, FeatureBuilder featureBuilder) {
		for (final String version : fixVersions) {
			if (version.contains("QA")) {
				featureBuilder = featureBuilder.releaseLabelQA(version);
			}
			if (version.contains("PROD")) {
				featureBuilder = featureBuilder.releaseLabelProd(version);
			}
		}
		return featureBuilder;
	}

	/**
	 * Calculate task start date end date of prod and qa releases
	 *
	 * @param subTasks the sub tasks
	 * @param status the status
	 * @param featureBuilder the feature builder
	 * @return the feature builder
	 */
	private FeatureBuilder calculateTaskStartDateEndDate(final List<SubTask> subTasks, final String status,
			final FeatureBuilder featureBuilder) {
		final Map<String, LocalDateTime> taskDates = calculateTaskDates(subTasks, TASK_START, TASK_END_PROD,
				TASK_END_QA, status.toUpperCase());

		final LocalDateTime taskStartDate = taskDates.get(TASK_START);
		final LocalDateTime taskEndDateQA = taskDates.get(TASK_END_QA);
		final LocalDateTime taskEndDatePROD = taskDates.get(TASK_END_PROD);

		if (taskStartDate != null) {
			featureBuilder.taskStartDate(taskStartDate);
			if (taskEndDateQA != null) {
				featureBuilder.mttdQaMonth(monthTrend(taskEndDateQA)).mttdQa(calculateMTD(taskStartDate, taskEndDateQA))
						.releaseDateQA(taskEndDateQA);
			}
			if (taskEndDatePROD != null) {
				featureBuilder.mttdProdMonth(monthTrend(taskEndDatePROD))
						.mttdProd(calculateMTD(taskStartDate, taskEndDatePROD)).releaseDateProd(taskEndDatePROD);
			}
		}
		return featureBuilder;
	}
	
	private HttpEntity<String> getRequest() {
		final String auth = jiraUser + ":" + jiraPassword;
		final byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
		final String authHeader = "Basic " + new String(encodedAuth);

		final HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authHeader);

		return new HttpEntity<>(headers);
	}

}
