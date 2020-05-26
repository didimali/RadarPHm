package com.radar.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.Dao.BasicInfoDao;
import com.radar.Entity.BasicInfo;
import com.radar.Repository.BasicInfoRepository;
import com.radar.Service.BasicInfoService;


@Service("BasicInfoServiceImpl")
public class BasicInfoServiceImpl implements BasicInfoService{
	
	@Autowired
    BasicInfoDao  basicInfoDao;
	
	@Autowired
	BasicInfoRepository basicInfoRepository;
	
	public List<BasicInfo> getAllBasicInfo(){
		return basicInfoDao.getAllBasicInfo();
	}	

	public List<BasicInfo> getAllParams() {
		return basicInfoDao.getAllParams();
	}
	@Override
	public Boolean updateParam(BasicInfo r) {
		try {
			basicInfoRepository.save(r);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public Boolean addParam(BasicInfo param) {
		try {
			basicInfoRepository.save(param);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
