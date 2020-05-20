package com.radar.Dao;

import java.util.List;

import com.radar.Entity.Radar;

public interface RadarDao {

	List<Radar> getAllRadars();
  
	List<Radar> getRadarsByManagerId(Integer managerIdInRadar);
  
	List<Object> getPreviousHI(String searchKey);

	Integer countDynamic(String searchKey);

	List<Object> getequipNameByRadar(String searchKey);
}
