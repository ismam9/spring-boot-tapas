package com.mine.springboot.tapas.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mine.springboot.tapas.app.models.dao.IBarDao;
import com.mine.springboot.tapas.app.models.entity.Bar;

@Service
public class BarImpl implements IBarService{
	
	@Autowired
	private IBarDao barDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Bar> findAll() {
		// TODO Auto-generated method stub
		return (List<Bar>) barDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Bar findOne(Long id) {
		// TODO Auto-generated method stub
		return barDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Bar save(Bar bar) {
		// TODO Auto-generated method stub
		return barDao.save(bar);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		barDao.deleteById(id);
	}
	

}
