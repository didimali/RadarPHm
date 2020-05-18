package com.radar.Service;

import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import com.radar.Entity.DynamicData;

public interface DynamicDataService {
	
	List<DynamicData> getAllDynamicDataByCollectDate(String startDate, String endDate);
	
	DefaultPieDataset getRadarCountsGroupByManager();

}
