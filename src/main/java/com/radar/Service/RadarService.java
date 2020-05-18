package com.radar.Service;

import java.util.List;

import com.radar.Entity.Radar;
import com.radar.Entity.healthResult;

public interface RadarService {
	
	//获取所有雷达数据
	List<Radar> getAllRadars();
	
	Boolean updateRadar(Radar r);
	
	Boolean addRadar(Radar r);

	List<Object> getPreviousHI(String searchKey);

	Integer countDynamic(String searchKey);

	List<Object> getequipNameByRadar(String searchKey);

}
