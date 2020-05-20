package com.radar.Dao;

import java.util.Date;
import java.util.List;

import com.radar.Entity.Record;

public interface RecordDao {
	List<Record> getAllRecords();

	List<Record> selectRecordByRadar(Integer RecordRadarId,String startTimeDate,String endTimeDate);

	List<Record> selectRecordByRadarId(Integer recordRadarId);

	List<Record> slectRecordByManager(Integer recordRadarId,String startTimeDate,String endTimeDate);

}
