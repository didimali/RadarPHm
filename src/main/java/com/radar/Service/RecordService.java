package com.radar.Service;

import java.util.List;

import com.radar.Entity.Record;

public interface RecordService {
	List<Record> getAllRecords();
	boolean add(Record record);
	List<Record> selectRecordByRadar(Integer RecordRadarId,String startTimeDate,String endTimeDate);
	List<Record> selectRecordByRadarId(Integer recordRadarId);

}
