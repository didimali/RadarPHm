package com.radar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.radar.Entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
	
	Manager findByManagerId(Integer id);

}
