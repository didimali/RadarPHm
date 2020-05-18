package com.radar.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radar.Dao.RadarDao;
import com.radar.Entity.Radar;
import com.radar.Repository.RadarRepository;
import com.radar.Service.RadarService;

@Service("RadarServiceImpl")
public class RadarServiceImpl implements RadarService{
	
	@Autowired
	RadarDao radarDao;
	
	@Autowired
	RadarRepository radarRepository;

	@Override
	public List<Radar> getAllRadars() {
		return radarDao.getAllRadars();
	}

	@Override
	public Boolean updateRadar(Radar r) {
		try {
			radarRepository.save(r);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public Boolean addRadar(Radar radar) {
		try {
			radarRepository.save(radar);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
