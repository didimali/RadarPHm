package com.radar.Dao;

import java.util.List;

import com.radar.Entity.Radar;

public interface RadarDao {

	List<Radar> getAllRadars();

	List<Radar> getRadarsByManagerId(Integer managerIdInRadar);



}
