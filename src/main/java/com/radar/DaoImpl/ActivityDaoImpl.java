package com.radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.radar.Dao.ActivityDao;
import com.radar.Entity.Activity;

@Repository("ActivityDaoImpl")

public class ActivityDaoImpl implements ActivityDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	//获取所有雷达数据
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getAllActivity() {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from activity where para_status = '0'";
		Query query = em.createNativeQuery(selectSql,Activity.class);
		List<Activity> list = query.getResultList();
		em.close();
		return list;
	}
}
