package com.radar.Service;

import java.util.List;

import com.radar.Entity.Manager;
import com.radar.Tools.ClassForMangerTable;

public interface ManagerService {
	
	//获取所有未删除的部队信息
	List<Manager> getAllManager();
	
	Boolean updateManager(Manager r);
	
	Boolean addManager(Manager r);
	
	//获取部队表格所需数据
	List<ClassForMangerTable> getDataForManagerTable();
	
}
