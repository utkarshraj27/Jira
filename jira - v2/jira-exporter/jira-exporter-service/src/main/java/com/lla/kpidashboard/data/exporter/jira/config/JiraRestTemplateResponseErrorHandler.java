package com.lla.kpidashboard.data.exporter.jira.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JiraRestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(final ClientHttpResponse httpResponse) throws IOException {

		return httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
	}

	@Override
	public void handleError(final ClientHttpResponse httpResponse) throws IOException {
		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			log.error("Encountered a Server Error, aborting ... Response :: {}", httpResponse.getStatusCode());
		} else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			log.error("Encountered Client Error, check the Jira Configuration, aborting ... Response :: {}",
					httpResponse.getStatusCode());
		}
	}
}
