package com.radar.Dao;

import java.util.List;

import com.radar.Entity.Radar;
import com.radar.Entity.healthResult;

public interface RadarDao {

	List<Radar> getAllRadars();

	List<Object> getPreviousHI(String searchKey);

	Integer countDynamic(String searchKey);

	List<Object> getequipNameByRadar(String searchKey);


}
