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
@Table(name="record")
public class Record {
	
	private Integer recordId;//记录id
	private Radar radarId;//	雷达id，外键
	private Activity activityId;//	记录类型id，外键
	private Date recordStartDate;//	活动起始时间
	private Date recordEndDate;//	活动结束时间
	private Integer withFault;//	活动期间是否发生故障，0:否；1：是；Default：0

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "recordId",unique=true,nullable=false, length = 11)
	public Integer getRecordId() {
		return recordId;
	}
	
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
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
	@JoinColumn(name="activityId")
	public Activity getActivityId() {
		return activityId;
	}

	public void setActivityId(Activity activityId) {
		this.activityId = activityId;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="recordStartDate")
	public Date getRecordStartDate() {
		return recordStartDate;
	}

	public void setRecordStartDate(Date recordStartDate) {
		this.recordStartDate = recordStartDate;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="recordEndDate")
	public Date getRecordEndDate() {
		return recordEndDate;
	}

	public void setRecordEndDate(Date recordEndDate) {
		this.recordEndDate = recordEndDate;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getWithFault() {
		return withFault;
	}

	public void setWithFault(Integer withFault) {
		this.withFault = withFault;
	}

}
