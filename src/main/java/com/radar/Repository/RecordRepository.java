package com.radar.Repository;

import org.springframework.data.repository.Repository;

import com.radar.Entity.Record;

public interface RecordRepository extends Repository<Record,Integer>{

	void save(Record record);

}
