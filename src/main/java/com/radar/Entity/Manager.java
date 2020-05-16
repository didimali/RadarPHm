package com.radar.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="manager")
public class Manager {
	
	private Integer managerId; //雷达所属部队id
	private String managerName; //雷达所属部队代号
	private String managerLocation; //雷达所属部队位置
	private Integer managerStatus; //雷达所属部队状态 0:存在；1：已删除 Default：0
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "managerId",unique=true,nullable=false, length = 11)
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	@Column(name ="managerName",length = 32,unique=true)
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	@Column(name ="managerLocation",length = 32)
	public String getManagerLocation() {
		return managerLocation;
	}
	public void setManagerLocation(String managerLocation) {
		this.managerLocation = managerLocation;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getManagerStatus() {
		return managerStatus;
	}
	public void setManagerStatus(Integer managerStatus) {
		this.managerStatus = managerStatus;
	}

}
