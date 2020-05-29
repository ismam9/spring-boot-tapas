package com.mine.springboot.tapas.app.models.services;

import java.util.List;

import com.mine.springboot.tapas.app.models.entity.Raciones;

public interface IRacionesService {
	
	public List<Raciones> findAllRaciones();
	
	public Raciones findOneRacion(Long id);
	
	public Raciones findRacionById(Long id);
	
	public Raciones save(Raciones racion);
	
	public void deleteRacion(Long id);
}
