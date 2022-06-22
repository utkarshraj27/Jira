package com.lla.kpidashboard.data.exporter.jira.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.lla.kpidashboard.data.exporter.jira.domain.Epic;
import com.lla.kpidashboard.data.exporter.jira.domain.Project;
import com.lla.kpidashboard.data.exporter.jira.service.JiraApiServices;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LambdaFunction implements RequestHandler<Map<String,Object>, String> {

	private JiraApiServices serviceBean;
	
	
	static AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
			LambdaFunction.class.getPackage().getName());

	    public LambdaFunction() {
	    	this.serviceBean = new JiraApiServices();
		   ctx.getAutowireCapableBeanFactory().autowireBean(this);
	       
	    }
	    
	    @Bean("serviceBean")
	    JiraApiServices beanWithOptionalDependency(JiraApiServices serviceBean) {
			return new JiraApiServices();
		}
	@Override
	public String handleRequest(Map<String, Object> input, Context context) {
		log.info("******started *****");
		 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		 log.info("getting the session factory here ***************");
	        try (Session session = sessionFactory.openSession()) {
	        	log.info("Executing 1 *****");
	            session.beginTransaction();
	            log.info("Executing 2 *****");
	        	List<Epic> activeEpic = new ArrayList<>();
            List<Epic> myEpic = loadAllData(Epic.class, session);
	           // for (Epic epic : myEpic) {
	            //	if(epic.getEpicStatus().equals("OPEN")) {
	            //	activeEpic.add(epic);
	            //	}
				//}
	            System.out.println("active Epic is"+myEpic);
	            
	            myEpic.forEach(epics -> {
	            	final String jql = "?jql=project = \"Project Evaluation\" AND issuetype in (Epic, Task, Story) AND \"Epic Link\" = "+epics.getEpicId();
					
					
					log.info("Jql Query executed...."+jql);
				serviceBean.saveStory(jql, epics, session);
	            });
	              
	            log.info("Executing 3 *****");
	            System.out.println(myEpic);
	            session.getTransaction().commit();
	            log.info("Executing 4 *****");
	        }
	        catch (Exception e) {
				// TODO: handle exception
	        	e.printStackTrace();
			}
	            
		
		return null;
}
	public  <T> List<T> loadAllData(Class<T> type, Session session) {
	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<T> criteria = builder.createQuery(type);
	    criteria.from(type);
	    List<T> data = session.createQuery(criteria).getResultList();
	    return data;
	  }
	
	
}
