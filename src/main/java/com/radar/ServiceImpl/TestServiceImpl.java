package com.radar.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.Dao.TestDao;
import com.radar.Entity.Manager;
import com.radar.Repository.ManagerRepository;
import com.radar.Service.TestService;

@Service("TestServiceImpl")
public class TestServiceImpl implements TestService{

	@Autowired
	ManagerRepository mangerRepository;
	
	@Autowired
	TestDao testDao;
	@Override
	public Manager testDatabase(Integer id) {
		return mangerRepository.findByManagerId(id);
	}
	
	@Override
	public List<Manager> testDatabase1(Integer id) {
		// TODO Auto-generated method stub
		return testDao.testDatabase1(id);
	}

}
