package com.radar.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="radar")
public class Radar {
	
	private Integer radarId;//雷达id
	private String radarName; //雷达编号
	private Manager managerId;//雷达所属部队id，外键
	private Integer type;//雷达型号，I型雷达：0， II型雷达：1，Default：0
	private Integer radarStatus;//雷达状态，0:存在；1：已删除，Default：0
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "radarId",unique=true,nullable=false, length = 11)
	public Integer getRadarId(){
		return radarId;
	}
	public void setRadarId(Integer radarId) {
		this.radarId = radarId;
	}
	@Column(name="radarName",length=32,unique=true)
	public String getRadarName() {
		return radarName;
	}
	public void setRadarName(String radarName) {
		this.radarName = radarName;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@ManyToOne
	@JoinColumn(name="managerId",nullable=false)
	public Manager getManagerId(){
		return managerId;
	}
	public void setManagerId(Manager managerId) {
		this.managerId = managerId;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getRadarStatus() {
		return radarStatus;
	}
	public void setRadarStatus(Integer radarStatus) {
		this.radarStatus = radarStatus;
	}
}
