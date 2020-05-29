package com.mine.springboot.tapas.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mine.springboot.tapas.app.models.dao.ITapasDao;
import com.mine.springboot.tapas.app.models.entity.Tapas;

@Service
public class TapasImpl implements ITapasService{
	
	@Autowired
	private ITapasDao tapasDao;
		
	@Override
	@Transactional(readOnly = true)
	public List<Tapas> findAllTapas() {
		// TODO Auto-generated method stub
		return (List<Tapas>) tapasDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tapas findOneTapa(Long id) {
		// TODO Auto-generated method stub
		return tapasDao.findOneFetchWithComidaAndBebida(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Tapas findTapaById(Long id) {
		// TODO Auto-generated method stub
		return tapasDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Tapas save(Tapas tapa) {
		// TODO Auto-generated method stub
		return tapasDao.save(tapa);
	}

	@Override
	@Transactional
	public void deleteTapa(Long id) {
		// TODO Auto-generated method stub
		tapasDao.deleteById(id);
	}



}
