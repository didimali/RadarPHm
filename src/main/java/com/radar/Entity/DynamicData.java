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


@Entity
@Table(name="dynamicData")
public class DynamicData {
	
	private Integer dataId;//数据id	
	private Radar radarId;//	所属雷达id,外键	
	private BasicInfo paramId;//	所属参数id，外键	
	private String dataVaule;//	参数值	
// 	private BasicInfo basicInfoId;//	所属参数id，外键	
	private Date collectDate;//	采集时间
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "dataId",unique=true,nullable=false, length = 11)
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	@ManyToOne
	@JoinColumn(name="radarId",nullable=false)
	public Radar getRadarId() {
		return radarId;
	}
	public void setRadarId(Radar radarId) {
		this.radarId = radarId;
	}
	
	@Column(name="dataValue",length=16)
	public String getDataValue() {
		return this.dataVaule;
	}
	public void setDataValue(String dataValue) {
		this.dataVaule = dataValue;
	}
	@Column(name="collectDate",nullable=false)
	public Date getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}
	@ManyToOne
	@JoinColumn(name="paramId",nullable=false)
	public BasicInfo getParamId() {
		return paramId;
	}
	public void setParamId(BasicInfo paramId) {
		this.paramId = paramId;
	}
	
}
