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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="forecastResult")
public class forecastResult {
	
	private Integer forecastResultId;//故障预测记录id
	private Radar radarId;//	所属雷达id,外键
	private faultType faultTypeId;//	故障类型id,外键
	private String forecastChance;//	预测故障概率
	private String forecastLocation;//	预测故障部位
	private Date forecastDate;//	故障发生时间
	private Date assessDate;//	评估时间


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name = "forecastResultId",unique=true,nullable=false, length = 11)
	public Integer getForecastResultId() {
		return forecastResultId;
	}
	
	public void setForecastResultId(Integer forecastResultId) {
		this.forecastResultId = forecastResultId;
	}
	@ManyToOne
	@JoinColumn(name="radarId")
	public Radar getRadarId() {
		return radarId;
	}
	public void setRadarId(Radar radarId) {
		this.radarId = radarId;
	}
	@ManyToOne
	@JoinColumn(name="faultTypeId")
	public faultType FaultTypeId() {
		return faultTypeId;
	}
	public void setFaultTypeId(faultType faultTypeId) {
		this.faultTypeId = faultTypeId;
	}
	
	@Column(name="forecastChance",length=16)
	public String getForecastChance() {
		return forecastChance;
	}

	public void setForecastChance(String forecastChance) {
		this.forecastChance = forecastChance;
	}
	
	@Column(name="forecastLocation",length=32)
	public String getForecastLocation() {
		return forecastLocation;
	}

	public void setForecastLocation(String forecastLocation) {
		this.forecastLocation = forecastLocation;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="forecastDate")
	public Date getForecastDate() {
		return forecastDate;
	}

	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="assessDate")
	public Date getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(Date assessDate) {
		this.assessDate = assessDate;
	}
	}
