package com.mine.springboot.tapas.app.models.services;

import java.util.List;


import com.mine.springboot.tapas.app.models.entity.Tapas;

public interface ITapasService {
	
	public List<Tapas> findAllTapas();
	
	public Tapas findOneTapa(Long id);
	
	public Tapas findTapaById(Long id);
	
	public Tapas save(Tapas tapa);
	
	public void deleteTapa(Long id);
}
