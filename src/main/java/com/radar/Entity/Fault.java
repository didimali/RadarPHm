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
@Table(name="fault")
public class Fault {
	
	private Integer faultId;//故障id

	private Record recordId;//	记录id，外键

	private faultType  faultTypeId;//	故障类型id，外键

	private String faultReason;//	故障原因（描述）

	private String faultLocation;//	故障部位
	
	private Date faultDate;//	故障时刻

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "faultId",unique=true,nullable=false, length = 11)
	public Integer getFaultId() {
		return faultId;
	}
	
	public void setFaultId(Integer faultId) {
		this.faultId = faultId;
	}
	@ManyToOne
	@JoinColumn(name="recordId")
	public Record getRecordId() {
		return recordId;
	}

	public void setRecordId(Record recordId) {
		this.recordId = recordId;
	}
	@ManyToOne
	@JoinColumn(name="faultTypeId")
	public faultType getFaultTypeId() {
		return faultTypeId;
	}

	public void setFaultTypeId(faultType faultTypeId) {
		this.faultTypeId = faultTypeId;
	}
	@Column(name="faultReason",length=32)
	public String getFaultReason() {
		return faultReason;
	}

	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}
	@Column(name="faultLocation",length=32)
	public String getFaultLocation() {
		return faultLocation;
	}

	public void setFaultLocation(String faultLocation) {
		this.faultLocation = faultLocation;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="faultDate")
	public Date getFaultDate() {
		return faultDate;
	}

	public void setFaultDate(Date faultDate) {
		this.faultDate = faultDate;
	}

}
