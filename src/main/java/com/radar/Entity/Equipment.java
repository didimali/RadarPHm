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
@Table(name="equipment")
public class Equipment {
	
	private Integer equipId;//雷达id
	private String equipName; //雷达编号
	private Radar radarId;//雷达所属部队id，外键
	private Integer equipStatus;//雷达状态，0:存在；1：已删除，Default：0
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "equipId",unique=true,nullable=false, length = 11)
	public Integer getEquipId() {
		return equipId;
	}
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	@Column(name="equipName",length=32,unique=true)
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	@ManyToOne
	@JoinColumn(name="radarId",nullable=false)
	public Radar getRadarId() {
		return radarId;
	}
	public void setRadarId(Radar radarId) {
		this.radarId = radarId;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getEquipStatus() {
		return equipStatus;
	}
	public void setEquipStatus(Integer equipStatus) {
		this.equipStatus = equipStatus;
	}

}
