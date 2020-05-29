package com.mine.springboot.tapas.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.springboot.tapas.app.models.dao.ICategoriaBebidaDao;
import com.mine.springboot.tapas.app.models.entity.CategoriaBebida;

@Service
public class CategoriaBebidaImpl implements ICategoriaBebidaService{
	
	@Autowired
	private ICategoriaBebidaDao categoriaBebidaDao;

	@Override
	public List<CategoriaBebida> findAll() {
		// TODO Auto-generated method stub
		return (List<CategoriaBebida>) categoriaBebidaDao.findAll();
	}
}
