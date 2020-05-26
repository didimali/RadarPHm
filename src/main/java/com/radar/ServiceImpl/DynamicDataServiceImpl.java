package com.radar.ServiceImpl;


import java.util.List;
import java.util.Iterator;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.Dao.DynamicDataDao;
import com.radar.Entity.DynamicData;
import com.radar.Repository.DynamicDataRepository;
import com.radar.Service.DynamicDataService;

@Service("DynamicDataServiceImpl")
public class DynamicDataServiceImpl implements DynamicDataService{
  
	@Autowired
	DynamicDataRepository dynamicDataRepository;
	@Autowired
	DynamicDataDao dynamicDataDao;
  
	public boolean add(DynamicData dynamicData) {
		try {
			dynamicDataRepository.save(dynamicData);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<DynamicData> selectDynamicDataByRadarId(Integer dynamicDataRadarId,
			String startTimeDate,String endTimeDate) {		
		return dynamicDataDao.selectDynamicDataByRadarId(dynamicDataRadarId,startTimeDate,endTimeDate);
	}


	//根据时间段获取雷达监测数据
	public List<DynamicData> getAllDynamicDataByCollectDate(String startDate, String endDate) {
		return dynamicDataDao.getAllDynamicDataByCollectDate(startDate,endDate);
	}

	//获取由部队分组的雷达数量
	@Override
	public DefaultPieDataset getRadarCountsGroupByManager() {
		List<Object> list = dynamicDataDao.getRadarCountsGroupByManager();
		DefaultPieDataset result = new DefaultPieDataset();
		Iterator<Object> it = list.iterator();
		Object[] obj = null;
		while (it.hasNext()) {
			obj = (Object[]) it.next();
			result.setValue(String.valueOf(obj[1]),Integer.parseInt(obj[0].toString()));
		}
		return result;
	}
	public List<DynamicData> selectDynamicDataByTime(String startTimeDate, String endTimeDate) {
		return dynamicDataDao.selectDynamicDataByTime(startTimeDate,endTimeDate);
	}	
}
