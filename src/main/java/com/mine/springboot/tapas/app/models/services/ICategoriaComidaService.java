package com.mine.springboot.tapas.app.models.services;

import java.util.List;

import com.mine.springboot.tapas.app.models.entity.CategoriaComida;

public interface ICategoriaComidaService {
	
	public List<CategoriaComida> findAll();
}
