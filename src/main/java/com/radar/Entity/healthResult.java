package com.radar.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="healthResult")
public class healthResult {
	
	private Integer healthResultId;//健康评估结果id
	private Radar radarId;//	所属雷达id，外键
	private String healthScores;//	评估打分	
	private Date assessDate;//	评估时间

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "healthResultId",unique=true,nullable=false, length = 11)
	public Integer getHealthResultId() {
		return healthResultId;
	}
	public void setHealthResultId(Integer healthResultId) {
		this.healthResultId = healthResultId;
	}
	@ManyToOne
	@JoinColumn(name="radarId")
	public Radar getRadarId() {
		return radarId;
	}
	public void setRadarId(Radar radarId) {
		this.radarId = radarId;
	}
	@Column(name="healthScores",length=8)
	public String getHealthScores() {
		return healthScores;
	}
	public void setHealthScores(String healthScores) {
		this.healthScores = healthScores;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="assessDate")
	public Date getAssessDate() {
		return assessDate;
	}
	public void setAssessDate(Date assessDate) {
		this.assessDate = assessDate;
	}

}
