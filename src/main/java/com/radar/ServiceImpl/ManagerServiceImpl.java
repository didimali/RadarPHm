package com.radar.ServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.Dao.ManagerDao;
import com.radar.Dao.RadarDao;
import com.radar.Entity.Manager;
import com.radar.Repository.ManagerRepository;
import com.radar.Service.ManagerService;
import com.radar.Tools.ClassForMangerTable;

@Service("ManagerServiceImpl")
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	ManagerDao managerDao;
	
	@Autowired
	ManagerRepository managerRepository;
	
	@Autowired
	RadarDao radarDao;

	@Override
	public List<Manager> getAllManager() {
		return managerDao.getAllManager();
	}
	
	//修改部队信息
	@Override
	public Boolean updateManager(Manager m) {
		try {
			managerRepository.save(m);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
    //添加部队信息
	public Boolean addManager(Manager manager) {
		try {
			managerRepository.save(manager);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//获取部队表格所需数据
	@SuppressWarnings("rawtypes")
	public List<ClassForMangerTable> getDataForManagerTable() {
		//获取部队基本信息
		List<Manager> managers = managerDao.getAllManager();
		//获取部队下属雷达数量信息
		List<Object> radarCounts = radarDao.getRadarCounts();
		
		List<ClassForMangerTable> result = new ArrayList<ClassForMangerTable>();
		for(Manager m:managers) {
			ClassForMangerTable c = new ClassForMangerTable(m.getManagerId(),m.getManagerName(),m.getManagerLocation(),0);
			result.add(c);
		}
		Iterator it = radarCounts.iterator();
		while(it.hasNext()) {
			Object[] o = (Object[]) it.next();
			for(ClassForMangerTable c:result)
				if(c.getManagerId() == (Integer)o[1])
					c.setRadarCounts(Integer.parseInt(o[0].toString()));
		}		
		return result;
	}

}
