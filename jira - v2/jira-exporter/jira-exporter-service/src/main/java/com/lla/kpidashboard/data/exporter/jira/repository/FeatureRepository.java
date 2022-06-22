package com.lla.kpidashboard.data.exporter.jira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lla.kpidashboard.data.exporter.jira.domain.Feature;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Integer> {

	@Query(value = "select coalesce(avg(mttd_qa),0) as mtdQA from feature where upper(status) in "
			+ "('RELEASE TO QA', 'IN UAT', 'QA COMPLETED','READY FOR PRODUCTION', 'RELEASED', 'DONE', 'CLOSED')", nativeQuery = true)
	public int meanTimeToDeployQA();

	@Query(value = "select coalesce(avg(mttd_prod),0) as mtdQA from feature where upper(status) in "
			+ "('RELEASED', 'DONE', 'CLOSED')", nativeQuery = true)
	public int meanTimeToDeployProd();

	@Query(value = "select mttd_qa_month monthYear, avg(mttd_qa) as mtd from "
			+ "feature where upper(status) in ('RELEASE TO QA', 'IN UAT', 'QA COMPLETED',"
			+ "'READY FOR PRODUCTION', 'RELEASED', 'DONE', 'CLOSED') and mttd_qa_month is not null group by mttd_qa_month", nativeQuery = true)
	public List<Trend> meanTimeToDeployQATrend();

	@Query(value = "select mttd_prod_month as monthYear, avg(mttd_prod) as mtd "
			+ "from feature where upper(status) in ('RELEASED', 'DONE', 'CLOSED') and mttd_prod_month is not null  group by mttd_prod_month", nativeQuery = true)
	public List<Trend> meanTimeToDeployProdTrend();

	@Query(value = "select release_labelqa releaseLabel, sum(effort)/3600 as effort from feature where release_labelqa is not null group by release_labelqa", nativeQuery = true)
	public List<ReleaseEffort> getEffortPerReleaseQA();

	@Query(value = "select release_label_prod releaseLabel, sum(effort)/3600 as effort from feature where release_label_prod is not null group by release_label_prod", nativeQuery = true)
	public List<ReleaseEffort> getEffortPerReleaseProd();

	@Query(value = "select release_labelqa releaseLabel, count(1) as effort from feature f where release_labelqa is not null group by release_labelqa", nativeQuery = true)
	public List<ReleaseEffort> getVolumePerReleaseQA();

	@Query(value = "select release_label_prod releaseLabel, count(1) as effort from feature f where release_label_prod is not null group by release_label_prod", nativeQuery = true)
	public List<ReleaseEffort> getVolumePerReleaseProd();

	@Query(value = "select avg(effort)/28800 from feature where release_labelqa is not null", nativeQuery = true)
	public int meanEffrotPerReleaseQA();

	@Query(value = "select avg(effort)/28800 from feature where release_label_prod is not null", nativeQuery = true)
	public int meanEffrotPerReleaseProd();

	@Query(value = "select avg(cnt) as cnt from (select release_labelqa,count(1) as cnt from feature f where release_labelqa is not null group by release_labelqa)a", nativeQuery = true)
	public double meanVolumePerReleaseQA();

	@Query(value = "select avg(cnt) as cnt from (select release_label_prod,count(1) as cnt from feature f where release_label_prod is not null group by release_label_prod)a", nativeQuery = true)
	public double meanVolumePerReleaseProd();

	public List<Feature> findAllByProjectId(final String projectId);

	public static interface Trend {

		String getMonthYear();

		Integer getMtd();

	}

	public static interface ReleaseEffort {

		String getReleaseLabel();

		long getEffort();

	}

}
