package com.radar.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.radar.Dao.RecordDao;
import com.radar.Entity.Record;
import com.radar.Repository.RecordRepository;
import com.radar.Service.RecordService;
@Service("RecordServiceImpl")
public class RecordServiceImpl	implements RecordService {
	@Autowired
	RecordDao recordDao;
	@Autowired
	RecordRepository recordRepository;
	@Override
	public List<Record> getAllRecords() {
		return recordDao.getAllRecords();
	}


	public boolean add(Record record) {
		try {
			recordRepository.save(record);
			return true;

		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public List<Record> selectRecordByRadar(Integer RecordRadarId,String startTimeDate,String endTimeDate) {
		// TODO Auto-generated method stub
		return recordDao.selectRecordByRadar(RecordRadarId,startTimeDate,endTimeDate);
	}


	public List<Record> selectRecordByRadarId(Integer recordRadarId) {
		// TODO Auto-generated method stub
		return recordDao.selectRecordByRadarId(recordRadarId);
	}


	public List<Record> slectRecordByManager(Integer recordRadarId,String startTimeDate,String endTimeDate) {
		// TODO Auto-generated method stub
		return recordDao.slectRecordByManager(recordRadarId,startTimeDate,endTimeDate);
	}

}
