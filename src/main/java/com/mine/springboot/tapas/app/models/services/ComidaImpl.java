package com.mine.springboot.tapas.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.springboot.tapas.app.models.dao.IComidaDao;
import com.mine.springboot.tapas.app.models.entity.Comida;

@Service
public class ComidaImpl implements IComidaService{
	
	@Autowired
	private IComidaDao comidaDao;

	@Override
	public List<Comida> findAllComida() {
		// TODO Auto-generated method stub
		return (List<Comida>) comidaDao.findAll();
	}
}
