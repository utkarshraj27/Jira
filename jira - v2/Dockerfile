# running from root directory
FROM openjdk:8-jdk-alpine
LABEL Abhijit Kulkarni <abhijit.kulkarni@cwc.com>
ENV JAVA_XMX "1024m"
ADD ./jira-exporter-api-0.0.1-SNAPSHOT.jar  app.jar
ENTRYPOINT ["java", "-jar", "./app.jar"] 

