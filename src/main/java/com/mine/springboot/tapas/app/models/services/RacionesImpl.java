package com.mine.springboot.tapas.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mine.springboot.tapas.app.models.dao.IRacionesDao;
import com.mine.springboot.tapas.app.models.entity.Raciones;

@Service
public class RacionesImpl implements IRacionesService{
	
	@Autowired
	private IRacionesDao racionesDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Raciones> findAllRaciones() {
		// TODO Auto-generated method stub
		return racionesDao.findAllFetchWithComidaAndBebida();
	}

	@Override
	@Transactional(readOnly = true)
	public Raciones findOneRacion(Long id) {
		// TODO Auto-generated method stub
		return racionesDao.findOneFetchWithComidaAndBebida(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Raciones findRacionById(Long id) {
		// TODO Auto-generated method stub
		return racionesDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Raciones save(Raciones racion) {
		// TODO Auto-generated method stub
		return racionesDao.save(racion);
	}

	@Override
	@Transactional
	public void deleteRacion(Long id) {
		// TODO Auto-generated method stub
		racionesDao.deleteById(id);
	}

}
