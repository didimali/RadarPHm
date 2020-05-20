package com.radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.radar.Dao.BasicInfoDao;
import com.radar.Entity.BasicInfo;

@Repository("BasicInfoDaoImpl")
public class BasicInfoDaoImpl implements BasicInfoDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	//
	@SuppressWarnings("unchecked")
	@Override
	public List<BasicInfo> getAllBasicInfo() {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from basic_info where param_status = '0'";
		Query query = em.createNativeQuery(selectSql,BasicInfo.class);
		List<BasicInfo> list = query.getResultList();
		em.close();
		return list;
	}
}
