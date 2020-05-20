package com.radar.DaoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.radar.Dao.RecordDao;
import com.radar.Entity.Record;

@Repository("RecordDaoImpl")

public class RecordDaoImpl implements RecordDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	//获取所有record数据
	@SuppressWarnings("unchecked")
	@Override
	public List<Record> getAllRecords() {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from record";
		Query query = em.createNativeQuery(selectSql,Record.class);
		List<Record> list = query.getResultList();
		em.close();
		return list;
	}
		@SuppressWarnings("unchecked")
		@Override
		public List<Record> selectRecordByRadar(Integer RecordRadarId,String startTimeDate,String endTimeDate) {
			EntityManager em = emf.createEntityManager();
			String selectSql = "select * from record where radar_id ='"+RecordRadarId+"' and  record_start_date >='"+startTimeDate+"'  and record_end_date <='"+endTimeDate+"' ";
			Query query = em.createNativeQuery(selectSql,Record.class);
			List<Record> list = query.getResultList();
			em.close();
			System.out.println("record:");

			System.out.println(list);
			return list;
		}
		@SuppressWarnings("unchecked")
		public List<Record> selectRecordByRadarId(Integer RecordRadarId) {
			EntityManager em = emf.createEntityManager();
			String selectSql = "select * from record where radar_id ='"+RecordRadarId+"'and with_fault='1'";
			Query query = em.createNativeQuery(selectSql,Record.class);
			List<Record> list = query.getResultList();
			em.close();
			return list;
		}
		@SuppressWarnings("unchecked")
		@Override
		public 	List<Record> slectRecordByManager(Integer recordRadarId,String startTimeDate,String endTimeDate){
			EntityManager em = emf.createEntityManager();
			String selectSql = "select * from record where radar_id ='"+recordRadarId+"' and  record_start_date >='"+startTimeDate+"'  and record_end_date <='"+endTimeDate+"'";
			Query query = em.createNativeQuery(selectSql,Record.class);
			List<Record> list = query.getResultList();
			em.close();
			return list;
		}

	
}
