package com.radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.radar.Dao.ManagerDao;
import com.radar.Entity.Manager;

@Repository("ManagerDaoImpl")
public class ManagerDaoImpl implements ManagerDao{
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	//查询所有未删除的部队信息
	@SuppressWarnings("unchecked")
	@Override
	public List<Manager> getAllManager() {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from manager where manager_status = '0'";
		Query query = em.createNativeQuery(selectSql,Manager.class);
		List<Manager> list = query.getResultList();
		em.close();
		return list;
	}

}
