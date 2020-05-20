package com.radar.Repository;

import org.springframework.data.repository.Repository;

import com.radar.Entity.BasicInfo;
import com.radar.Entity.DynamicData;

public interface DynamicDataRepository extends Repository<BasicInfo,Integer>{

	void save(DynamicData dynamicData);

}
