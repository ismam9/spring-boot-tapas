package com.mine.springboot.tapas.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.tapas.app.models.entity.Tapas;

public interface ITapasDao extends CrudRepository<Tapas, Long>{
	
	@Query("select t from Tapas t left join fetch t.comida c left join fetch t.bebida b")	
	public List<Tapas> findAllFetchWithComidaAndBebida();
	
	@Query("select t from Tapas t left join fetch t.comida c left join fetch t.bebida b where t.id=?1")
	public Tapas findOneFetchWithComidaAndBebida(Long id);
}
