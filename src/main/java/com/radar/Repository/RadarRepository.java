package com.radar.Repository;

import org.springframework.data.repository.Repository;

import com.radar.Entity.Radar;


public interface RadarRepository extends Repository<Radar,Integer>{
	
	void save(Radar r);

}
