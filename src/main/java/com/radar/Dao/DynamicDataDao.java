package com.radar.Dao;

import java.util.List;

import com.radar.Entity.DynamicData;

public interface DynamicDataDao {

	List<DynamicData> selectDynamicDataByRadarId(Integer dynamicDataRadarId,String startTimeDate,String endTimeDate);
  
	List<DynamicData> getAllDynamicDataByCollectDate(String startDate, String endDate);

	List<Object> getRadarCountsGroupByManager();

	List<DynamicData> selectDynamicDataByTime(String startTimeDate, String endTimeDate);

}
