package com.radar.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.Dao.ManagerDao;
import com.radar.Entity.Manager;
import com.radar.Service.ManagerService;

@Service("ManagerServiceImpl")
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	ManagerDao managerDao;

	@Override
	public List<Manager> getAllManager() {
		return managerDao.getAllManager();
	}

}
