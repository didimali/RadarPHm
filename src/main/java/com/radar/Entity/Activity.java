package com.radar.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="activity")
public class Activity {
	private Integer activityId;//记录类型id
	private String activityName;//记录类型name
	private Integer paraStatus;//记录类型状态，0:存在；1：已删除，Default：0
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "activityId",unique=true,nullable=false, length = 11)
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	@Column(name = "activityName",unique=true,nullable=false,length = 16)
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getParaStatus() {
		return paraStatus;
	}
	public void setParaStatus(Integer paraStatus) {
		this.paraStatus = paraStatus;
	}
}
