package com.radar.Repository;

import org.springframework.data.repository.Repository;

import com.radar.Entity.Manager;

public interface ManagerRepository extends Repository<Manager,Integer> {
	
	Manager findByManagerId(Integer id);
	
	void save(Manager m);

}
