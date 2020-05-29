package com.mine.springboot.tapas.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.tapas.app.models.entity.Comida;

public interface IComidaDao extends CrudRepository<Comida, Long>{

}
