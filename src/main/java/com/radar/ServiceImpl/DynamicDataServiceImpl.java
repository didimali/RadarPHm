package com.radar.ServiceImpl;

import java.util.List;

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
	public List<DynamicData> selectDynamicDataByRadarId(Integer dynamicDataRadarId,String startTimeDate,String endTimeDate) {
		// TODO Auto-generated method stub
		return dynamicDataDao.selectDynamicDataByRadarId(dynamicDataRadarId,startTimeDate,endTimeDate);
	}
} 
