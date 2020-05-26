package com.radar.Dao;

import java.util.List;

import com.radar.Entity.Fault;

public interface FaultRecordDao {
	List<Fault>getAllFaultRecords();

	List<Fault> selectFaultRecordByRecordId(Integer recordId,String startTimeDate,String endTimeDate);

	List<Fault> selectFaultRecordByTime(String startTimeDate, String endTimeDate);
}
