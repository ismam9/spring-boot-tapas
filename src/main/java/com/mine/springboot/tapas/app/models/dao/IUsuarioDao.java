package com.mine.springboot.tapas.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.tapas.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);

}
