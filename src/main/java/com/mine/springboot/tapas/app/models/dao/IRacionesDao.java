package com.mine.springboot.tapas.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.tapas.app.models.entity.Raciones;

public interface IRacionesDao extends CrudRepository<Raciones, Long>{
	
	@Query("select r from Raciones r left join fetch r.comida c left join fetch r.bebida b")	
	public List<Raciones> findAllFetchWithComidaAndBebida();
	
	@Query("select r from Raciones r left join fetch r.comida c left join fetch r.bebida b where r.id=?1")
	public Raciones findOneFetchWithComidaAndBebida(Long id);
}
