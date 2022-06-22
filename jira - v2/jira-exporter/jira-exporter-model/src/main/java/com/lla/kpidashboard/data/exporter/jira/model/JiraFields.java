package com.lla.kpidashboard.data.exporter.jira.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraFields implements Serializable {

	private static final long serialVersionUID = -8267421370022161795L;

	@JsonProperty("issuetype")
	private JiraIssuetype issuetype;

	@JsonProperty("components")
	private List<JiraComponent> components;

	//Added For Ecommerce-Panama
	@JsonProperty("issuelinks")
	private List<JiraIssueLinks> issuelinks;

	//Added For Ecommerce-Panama
	@JsonProperty("subtasks")
	private List<JiraIssueSubtasks> subtasks;

	@JsonProperty("timespent")
	private String timespent;

	@JsonProperty("timeoriginalestimate")
	private String timeoriginalestimate;

	@JsonProperty("description")
	private String description;

	@JsonProperty("project")
	private JiraProject project;

	@JsonProperty("fixVersions")
	private List<JiraFixVersion> fixVersions;

	@JsonProperty("aggregatetimespent")
	private long aggregatetimespent;

	@JsonProperty("resolution")
	private JiraResolution resolution;

	@JsonProperty("aggregatetimeestimate")
	private String aggregatetimeestimate;

	@JsonProperty("resolutiondate")
	private String resolutiondate;

	@JsonProperty("workratio")
	private long workratio;

	@JsonProperty("summary")
	private String summary;

	@JsonProperty("lastViewed")
	private String lastViewed;

	@JsonProperty("watches")
	private JiraWatches watches;

	@JsonProperty("creator")
	private JiraActor creator;

	@JsonProperty("created")
	private String created;

	@JsonProperty("reporter")
	private JiraActor reporter;

	@JsonProperty("aggregateprogress")
	private JiraProgress aggregateprogress;

	@JsonProperty("priority")
	private JiraPriority priority;

	@JsonProperty("labels")
	private List<String> labels;

	@JsonProperty("environment")
	private String environment;

	@JsonProperty("timeestimate")
	private String timeestimate;

	@JsonProperty("aggregatetimeoriginalestimate")
	private String aggregatetimeoriginalestimate;

	@JsonProperty("versions")
	private List<String> versions;

	@JsonProperty("duedate")
	private String duedate;

	@JsonProperty("progress")
	private JiraProgress progress;

	@JsonProperty("votes")
	private JiraVotes votes;


	@JsonProperty("updated")
	private String updated;

	@JsonProperty("status")
	private JiraStatus status;

	@JsonProperty("customfield_10200")
	private String taskStartDate;

	@JsonProperty("customfield_10201")
	private String taskEndDate;

	public JiraIssuetype getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(final JiraIssuetype issuetype) {
		this.issuetype = issuetype;
	}

	public List<JiraComponent> getComponents() {
		return components;
	}

	public void setComponents(final List<JiraComponent> components) {
		this.components = components;
	}

	public String getTimespent() {
		return timespent;
	}

	public void setTimespent(final String timespent) {
		this.timespent = timespent;
	}

	public String getTimeoriginalestimate() {
		return timeoriginalestimate;
	}

	public void setTimeoriginalestimate(final String timeoriginalestimate) {
		this.timeoriginalestimate = timeoriginalestimate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public JiraProject getProject() {
		return project;
	}

	public void setProject(final JiraProject project) {
		this.project = project;
	}

	public List<JiraFixVersion> getFixVersions() {
		return fixVersions;
	}

	public void setFixVersions(final List<JiraFixVersion> fixVersions) {
		this.fixVersions = fixVersions;
	}

	public long getAggregatetimespent() {
		return aggregatetimespent;
	}

	public void setAggregatetimespent(final long aggregatetimespent) {
		this.aggregatetimespent = aggregatetimespent;
	}

	public JiraResolution getResolution() {
		return resolution;
	}

	public void setResolution(final JiraResolution resolution) {
		this.resolution = resolution;
	}

	public String getAggregatetimeestimate() {
		return aggregatetimeestimate;
	}

	public void setAggregatetimeestimate(final String aggregatetimeestimate) {
		this.aggregatetimeestimate = aggregatetimeestimate;
	}

	public String getResolutiondate() {
		return resolutiondate;
	}

	public void setResolutiondate(final String resolutiondate) {
		this.resolutiondate = resolutiondate;
	}

	public long getWorkratio() {
		return workratio;
	}

	public void setWorkratio(final long workratio) {
		this.workratio = workratio;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	public String getLastViewed() {
		return lastViewed;
	}

	public void setLastViewed(final String lastViewed) {
		this.lastViewed = lastViewed;
	}

	public JiraWatches getWatches() {
		return watches;
	}

	public void setWatches(final JiraWatches watches) {
		this.watches = watches;
	}

	public JiraActor getCreator() {
		return creator;
	}

	public void setCreator(final JiraActor creator) {
		this.creator = creator;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(final String created) {
		this.created = created;
	}

	public JiraActor getReporter() {
		return reporter;
	}

	public void setReporter(final JiraActor reporter) {
		this.reporter = reporter;
	}

	public JiraProgress getAggregateprogress() {
		return aggregateprogress;
	}

	public void setAggregateprogress(final JiraProgress aggregateprogress) {
		this.aggregateprogress = aggregateprogress;
	}

	public JiraPriority getPriority() {
		return priority;
	}

	public void setPriority(final JiraPriority priority) {
		this.priority = priority;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(final List<String> labels) {
		this.labels = labels;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(final String environment) {
		this.environment = environment;
	}

	public String getTimeestimate() {
		return timeestimate;
	}

	public void setTimeestimate(final String timeestimate) {
		this.timeestimate = timeestimate;
	}

	public String getAggregatetimeoriginalestimate() {
		return aggregatetimeoriginalestimate;
	}

	public void setAggregatetimeoriginalestimate(final String aggregatetimeoriginalestimate) {
		this.aggregatetimeoriginalestimate = aggregatetimeoriginalestimate;
	}

	public List<String> getVersions() {
		return versions;
	}

	public void setVersions(final List<String> versions) {
		this.versions = versions;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(final String duedate) {
		this.duedate = duedate;
	}

	public JiraProgress getProgress() {
		return progress;
	}

	public void setProgress(final JiraProgress progress) {
		this.progress = progress;
	}


	public JiraVotes getVotes() {
		return votes;
	}

	public void setVotes(final JiraVotes votes) {
		this.votes = votes;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(final String updated) {
		this.updated = updated;
	}

	public JiraStatus getStatus() {
		return status;
	}

	public void setStatus(final JiraStatus status) {
		this.status = status;
	}

	public List<JiraIssueLinks> getIssuelinks() {
		return issuelinks;
	}

	public void setIssuelinks(final List<JiraIssueLinks> issuelinks) {
		this.issuelinks = issuelinks;
	}

	public List<JiraIssueSubtasks> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(final List<JiraIssueSubtasks> subtasks) {
		this.subtasks = subtasks;
	}

	public String getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(final String taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public String getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(final String taskEndDate) {
		this.taskEndDate = taskEndDate;
	}
}
