package com.radar.Dao;

import java.util.List;

import com.radar.Entity.DynamicData;

public interface DynamicDataDao {

	List<DynamicData> getAllDynamicDataByCollectDate(String startDate, String endDate);

	List<Object> getRadarCountsGroupByManager();

}
