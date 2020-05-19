package com.radar.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.Dao.BasicInfoDao;
import com.radar.Entity.BasicInfo;
import com.radar.Service.BasicInfoService;
@Service("BasicInfoServiceImpl")
public class BasicInfoServiceImpl implements BasicInfoService{
	@Autowired
    BasicInfoDao  basicInfoDao;
	public List<BasicInfo> getAllBasicInfo(){
		return basicInfoDao.getAllBasicInfo();
	}
}
