package com.lla.kpidashboard.data.exporter.jira.utility;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

public class Utility {
	private Utility() {

	}

	private static final List<String> TASKSTAGES = Arrays.asList("OPEN", "DONE", "CLOSED");
	private static final List<String> PRODSTAGES = Arrays.asList("RELEASED", "DONE", "CLOSED");
	private static final List<String> QASTAGES = Arrays.asList("RELEASE TO QA", "IN UAT", "QA COMPLETED",
			"READY FOR PRODUCTION", "RELEASED", "DONE", "CLOSED");



	public static List<String> getProdStages() {
		return PRODSTAGES;
	}

	public static List<String> getTaskstages() {
		return TASKSTAGES;
	}

	public static List<String> getQAStages() {
		return QASTAGES;
	}

	public static boolean isWithinRange(final LocalDate testDate, final LocalDate startDate, final LocalDate endDate) {
		return !(testDate.isBefore(startDate) || testDate.isAfter(endDate));
	}

	public static String getSequence(int i) {

		i = 12 - i;
		if (i > 9) {
			return "" + i;
		}
		return "0" + i;
	}

	public static String getBasicAuthHeader(final String jiraUser, final String jiraPassword) {

		final String auth = jiraUser + ":" + jiraPassword;
		final byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
		return "Basic " + new String(encodedAuth);
	}

	public static String getJiraSearchUrl(final boolean insecure, final String host, final String port) {

		String target = null;
		if (insecure) {
			target = "http://";
		} else {
			target = "https://";
		}
		target = target + host + ":" + port + "/rest/api/2/search";

		return target;
	}

	public static String monthTrend(final LocalDateTime ldt) {
		return ldt.getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + "-" + ldt.getYear();
	}

	public static int calculateMTD(final LocalDateTime taskStartDate, final LocalDateTime taskEndDate) {
		return (int) ChronoUnit.DAYS.between(taskStartDate, taskEndDate);
	}
}
