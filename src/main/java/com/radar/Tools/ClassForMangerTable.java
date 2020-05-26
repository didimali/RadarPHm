package com.radar.Tools;

/*
 * 部队表格的工具辅助类
 */
public class ClassForMangerTable {
	
	private Integer managerId;//部队id
	private String managerName;//部队编号
	private String managerLocation;//部队位置
	private Integer radarCounts;//部队下辖雷达数量
	
	public ClassForMangerTable() {
		
	}
	
	public ClassForMangerTable(Integer managerId,String managerName,String managerLocation,Integer radarCounts) {
		this.managerId = managerId;
		this.managerName = managerName;
		this.managerLocation = managerLocation;
		this.radarCounts = radarCounts;
		
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerLocation() {
		return managerLocation;
	}
	public void setManagerLocation(String managerLocation) {
		this.managerLocation = managerLocation;
	}
	public Integer getRadarCounts() {
		return radarCounts;
	}
	public void setRadarCounts(Integer radarCounts) {
		this.radarCounts = radarCounts;
	}

}
