package com.radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.radar.Dao.FaultTypeDao;
import com.radar.Entity.faultType;

@Repository("FaultTypeDaoImpl")
public class FaultTypeDaoImpl implements FaultTypeDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	//查询所有未删除的部队信息
	@SuppressWarnings("unchecked")
	@Override
	public 	List<faultType> getAllFaultType() {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from fault_Type";
		Query query = em.createNativeQuery(selectSql,faultType.class);
		List<faultType> list = query.getResultList();
		em.close();
		return list;
	}

}
