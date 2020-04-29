package com.radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.radar.Dao.TestDao;
import com.radar.Entity.Manager;

@Repository("TestDaoImpl")
public class TestDaoImpl implements TestDao{
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Manager> testDatabase1(Integer id) {
		
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from manager";
		Query query = em.createNativeQuery(selectSql,Manager.class);
		List<Manager> list = query.getResultList();
		em.close();
		return list;
	}

}
