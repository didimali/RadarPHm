package com.radar.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="faultType")
public class faultType {
	
	private Integer faultTypeId;//故障id
	private String faultName;//	故障名称
	private String faultPrinciple;//	故障机理
	private Integer faultLevel;//	故障级别

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "faultTypeId",unique=true,nullable=false, length = 11)
	public Integer getFaultTypeId() {
		return faultTypeId;
	}
	
	public void setFaultTypeId(Integer faultTypeId) {
		this.faultTypeId = faultTypeId;
	}
	
	@Column(name="faultName",length=32)
	public String getFaultName() {
		return faultName;
	}

	public void setFaultName(String faultName) {
		this.faultName = faultName;
	}
	@Column(name="faultPrinciple",length=32)
	public String getFaultPrinciple() {
		return faultPrinciple;
	}

	public void setFaultPrinciple(String faultPrinciple) {
		this.faultPrinciple = faultPrinciple;
	}
	@Column(name="faultLevel",length=11)
	public Integer getFaultLevel() {
		return faultLevel;
	}

	public void setFaultLevel(Integer faultLevel) {
		this.faultLevel = faultLevel;
	}
}
