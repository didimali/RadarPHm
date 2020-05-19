package com.radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.radar.Dao.RadarDao;
import com.radar.Entity.Radar;


@Repository("RadarDaoImpl")
public class RadarDaoImpl implements RadarDao{
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	//获取所有雷达数据
	@SuppressWarnings("unchecked")
	@Override
	public List<Radar> getAllRadars() {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from radar";
		Query query = em.createNativeQuery(selectSql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}
	public List<Radar> getRadarsByManagerId(Integer managerIdInRadar){
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from radar where manager_id = '"+managerIdInRadar+"'";
		Query query = em.createNativeQuery(selectSql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}

}
