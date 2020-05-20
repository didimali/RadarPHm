package com.radar.Service;

import java.util.List;

import com.radar.Entity.Fault;

public interface FaultRecordService {
	List<Fault> getAllFaultRecords();
	Boolean add(Fault fault);
	List<Fault> selectFaultRecordByRecordId(Integer recordId,String startTimeDate,String endTimeDate);
}
