package com.radar.Service;

import java.util.List;

import com.radar.Entity.BasicInfo;

public interface BasicInfoService {
	
	List<BasicInfo> getAllBasicInfo();
	
	//获取所有未删除的型号信息
	List<BasicInfo> getAllParams();
	
	Boolean updateParam(BasicInfo r);
		
	Boolean addParam(BasicInfo r);
}
