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
import com.radar.Entity.healthResult;

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
		String selectSql = "select * from radar where radar_status=0";
		Query query = em.createNativeQuery(selectSql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}

	//获取历史健康数据（最新5条）
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getPreviousHI(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String selectSql =" select health_result_id,assess_date,health_scores from health_result "
				+ "left join radar on health_result.radar_id=radar.radar_id where "
				+ "radar.radar_name='" + searchKey + "'order by health_result_id desc limit 5";  		
		Query query = em.createNativeQuery(selectSql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}
	
	//健康评估判断是否有雷达数据
	@SuppressWarnings("unchecked")
	@Override
	public Integer countDynamic(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String countSql = "select count(*) from dynamic_data left join "
				+ "radar on dynamic_data.radar_id=radar.radar_id where radar.radar_name='" + searchKey + "'"; 
		Query query = em.createNativeQuery(countSql);
		List<Object> num = query.getResultList();
		em.close();
		return Integer.parseInt(num.get(0).toString());
	}
	
	//根据radar名称获取旗下所有设备名称
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getequipNameByRadar(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String selectSql =" select equip_name from equipment "
				+ "left join radar on equipment.radar_id=radar.radar_id where "
				+ "radar.radar_name='" + searchKey + "'and equip_status=0";  		
		Query query = em.createNativeQuery(selectSql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}
	
	
	
}
