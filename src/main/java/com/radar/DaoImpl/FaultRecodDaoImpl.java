package com.radar.DaoImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.radar.Dao.FaultRecordDao;
import com.radar.Entity.Fault;

@Repository("FaultRecodDaoImpl")
public class FaultRecodDaoImpl implements FaultRecordDao{
	

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	//查询所有故障
	@SuppressWarnings("unchecked")
	@Override
	public 	List<Fault> getAllFaultRecords() {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from fault";
		Query query = em.createNativeQuery(selectSql,Fault.class);
		List<Fault> list = query.getResultList();
		em.close();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Fault> selectFaultRecordByRecordId(Integer recordId,String startTimeDate,String endTimeDate) {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from fault where record_id='"+recordId+"'and fault_date between '"+startTimeDate+"'and '"+endTimeDate+"'";
		Query query = em.createNativeQuery(selectSql,Fault.class);
		List<Fault> list = query.getResultList();
		em.close();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Fault> selectFaultRecordByTime(String startTimeDate, String endTimeDate){
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from fault where fault_date between '"+startTimeDate+"'and '"+endTimeDate+"'";
		Query query = em.createNativeQuery(selectSql,Fault.class);
		List<Fault> list = query.getResultList();
		em.close();
		return list;
	}

	
}
