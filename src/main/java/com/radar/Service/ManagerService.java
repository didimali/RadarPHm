package com.radar.Service;

import java.util.List;

import com.radar.Entity.Manager;

public interface ManagerService {
	
	//获取所有未删除的部队信息
	List<Manager> getAllManager();

}
