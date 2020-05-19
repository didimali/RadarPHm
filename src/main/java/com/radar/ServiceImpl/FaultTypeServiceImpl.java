package com.radar.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.Dao.FaultTypeDao;
import com.radar.Entity.Fault;
import com.radar.Entity.faultType;
import com.radar.Service.FaultTypeService;

@Service("FaultTypeServiceImpl")
public class FaultTypeServiceImpl implements FaultTypeService{
	@Autowired
	FaultTypeDao faultTypeDao;
	@Override
	public List<faultType> getAllFaultType(){
		return faultTypeDao.getAllFaultType();
	}
	
	

}
