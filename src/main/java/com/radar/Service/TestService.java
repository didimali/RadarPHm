package com.radar.Service;

import java.util.List;

import com.radar.Entity.Manager;

public interface TestService {
	
	//测试直接用jpa访问数据库
	Manager testDatabase(Integer id);
	
	//测试用entityManagerFactory访问数据库
	
	List<Manager> testDatabase1(Integer id);

}
