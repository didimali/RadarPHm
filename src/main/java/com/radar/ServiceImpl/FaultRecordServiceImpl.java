package com.radar.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.Dao.FaultRecordDao;
import com.radar.Entity.Fault;
import com.radar.Repository.FaultRepository;
import com.radar.Service.FaultRecordService;
@Service("FaultRecordServiceImpl")
public class FaultRecordServiceImpl implements FaultRecordService{
	@Autowired
	FaultRecordDao faultRecordDao;
	@Autowired
	FaultRepository faultRepository;
	@Override
	public List<Fault> getAllFaultRecords(){
		return faultRecordDao.getAllFaultRecords();
	}
	public  Boolean add(Fault fault) {
		try {
			faultRepository.save(fault);
			return true;

		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<Fault> selectFaultRecordByRecordId(Integer recordId,String startTimeDate,String endTimeDate) {
		// TODO Auto-generated method stub
		return faultRecordDao.selectFaultRecordByRecordId(recordId,startTimeDate,endTimeDate);
	}
}
