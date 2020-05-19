package com.radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.radar.Dao.DynamicDataDao;
import com.radar.Entity.Activity;
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
}
