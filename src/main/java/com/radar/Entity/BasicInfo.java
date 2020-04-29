package com.radar.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="basicInfo")
public class BasicInfo {
	
	private Integer paramId; /* 主键，自增，参数id */
	private Integer type; /* 雷达型号，I型雷达：0 II型雷达：1；Default：0 */
	private String paramName; /*参数名字*/
	private String paramRate; /*参数额定值，开关量：0*/
	private String paramMax; /*参数最小值，开关量：0*/
	private String paramMin; /*参数最大值，开关量：0*/
	private Integer paramStatus;/* 参数状态，0:存在；1：已删除 ；Default：0 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "paramId",unique=true,nullable=false, length = 11)
	public Integer getParamId() {
		return paramId;
	}
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "paramName",length = 32)
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	@Column(name = "paramRate",length = 32)
	public String getParamRate() {
		return paramRate;
	}
	public void setParamRate(String paramRate) {
		this.paramRate = paramRate;
	}
	@Column(name = "paramMax",length = 32)
	public String getParamMax() {
		return paramMax;
	}
	public void setParamMax(String paramMax) {
		this.paramMax = paramMax;
	}
	@Column(name = "paramMin",length = 32)
	public String getParamMin() {
		return paramMin;
	}
	public void setParamMin(String paramMin) {
		this.paramMin = paramMin;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getParamStatus() {
		return paramStatus;
	}
	public void setParamStatus(Integer paramStatus) {
		this.paramStatus = paramStatus;
	}
}
