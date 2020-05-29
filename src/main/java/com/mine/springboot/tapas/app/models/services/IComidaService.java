package com.mine.springboot.tapas.app.models.services;

import java.util.List;

import com.mine.springboot.tapas.app.models.entity.Comida;

public interface IComidaService {

	public List<Comida> findAllComida();
}
