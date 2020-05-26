package com.radar.Repository;

import org.springframework.data.repository.Repository;


import com.radar.Entity.BasicInfo;
import com.radar.Entity.Manager;


public interface BasicInfoRepository extends Repository<BasicInfo,Integer> {
	
	Manager findByParamId(Integer id);

	void save(BasicInfo r);

}
