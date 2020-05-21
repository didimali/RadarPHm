package com.radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.radar.Dao.DynamicDataDao;
import com.radar.Entity.DynamicData;

@Repository("DynamicDataDaoImpl")
public class DynamicDataDaoImpl implements DynamicDataDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	@SuppressWarnings("unchecked")
	@Override
	public List<DynamicData>selectDynamicDataByRadarId(Integer dynamicDataRadarId,String startTimeDate,String endTimeDate){
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from dynamic_data where radar_id = '"+dynamicDataRadarId+"'and collect_date between '"+startTimeDate+"'and '"+endTimeDate+"'";
    Query query = em.createNativeQuery(selectSql,DynamicData.class);
		List<DynamicData> list = query.getResultList();
		em.close();
		return list;
  }


	@SuppressWarnings("unchecked")
	@Override
	public List<DynamicData> getAllDynamicDataByCollectDate(String startDate, String endDate) {
		EntityManager em = emf.createEntityManager();
		String param1 = startDate;
		String param2 = endDate;
		String selectSql = "select * from dynamic_data where collect_date between '"+param1+"' and '"+param2+"' ";
		Query query = em.createNativeQuery(selectSql,DynamicData.class);
		List<DynamicData> list = query.getResultList();
		em.close();
		return list;
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getRadarCountsGroupByManager() {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select COUNT(radar_id) as radarCounts,manager.manager_name as managerName"
							+" from manager left join radar on manager.manager_id = radar.manager_id " 
							+" where manager.manager_status = 0 and radar.radar_status = 0"
							+" group by radar.manager_id";
		Query query = em.createNativeQuery(selectSql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}
    
}
