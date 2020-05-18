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
	
	private int test = 3;
	
	public void setTest(int test) {
		System.out.println("beforeSet: "+this.test);
		this.test = test;
		System.out.println("afterSet: "+this.test);
	}
	
	public void setTest() {
		System.out.println("beforeSet: "+this.test);
		System.out.println("afterSet: "+this.test);
	}
	
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
