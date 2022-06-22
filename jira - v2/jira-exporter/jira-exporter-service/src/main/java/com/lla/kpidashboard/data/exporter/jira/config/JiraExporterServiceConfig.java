package com.lla.kpidashboard.data.exporter.jira.config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class JiraExporterServiceConfig {

	@Value("${http.client.ssl.trust-store}")
	private ClassPathResource keyStore;

	@Value("${http.client.ssl.trust-store-password}")
	private String keyStorePassword;

	@Bean
	public RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.errorHandler(new JiraRestTemplateResponseErrorHandler()).build();
	}

	@Bean
	public RestTemplate restSecureTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
	CertificateException, IOException {

		final SSLContext sslContext = new SSLContextBuilder()
				.loadTrustMaterial(keyStore.getURL(), keyStorePassword.toCharArray()).build();
		final SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
		final HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplate(factory);
	}

}
