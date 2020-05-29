package com.mine.springboot.tapas.app.models.services;

import java.util.List;

import com.mine.springboot.tapas.app.models.entity.Bar;

public interface IBarService {
	
	public List<Bar> findAll();
	
	public Bar findOne(Long id);
	
	public Bar save(Bar bar);
	
	public void delete(Long id);
}
