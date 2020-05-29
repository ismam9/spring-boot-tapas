package com.mine.springboot.tapas.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.springboot.tapas.app.models.dao.ICategoriaComidaDao;
import com.mine.springboot.tapas.app.models.entity.CategoriaComida;

@Service
public class CategoriaComidaImpl implements ICategoriaComidaService{
	
	@Autowired
	private ICategoriaComidaDao categoriaComidaDao;

	@Override
	public List<CategoriaComida> findAll() {
		// TODO Auto-generated method stub
		return (List<CategoriaComida>) categoriaComidaDao.findAll();
	}
}
