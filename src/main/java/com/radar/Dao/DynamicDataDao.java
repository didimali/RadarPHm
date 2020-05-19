package com.radar.Dao;

import java.util.List;

import com.radar.Entity.DynamicData;

public interface DynamicDataDao {

	List<DynamicData> selectDynamicDataByRadarId(Integer dynamicDataRadarId,String startTimeDate,String endTimeDate);

}
