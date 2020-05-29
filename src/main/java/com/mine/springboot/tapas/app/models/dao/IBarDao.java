package com.mine.springboot.tapas.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.tapas.app.models.entity.Bar;

public interface IBarDao extends CrudRepository<Bar, Long>{

}
