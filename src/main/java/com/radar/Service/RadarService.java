package com.radar.Service;

import java.util.List;

import com.radar.Entity.Radar;

public interface RadarService {
	
	//获取所有未删除的雷达数据
	List<Radar> getAllRadars();
	
	Boolean updateRadar(Radar r);
	
	Boolean addRadar(Radar r);

}
