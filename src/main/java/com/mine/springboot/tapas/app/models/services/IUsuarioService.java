package com.mine.springboot.tapas.app.models.services;

import com.mine.springboot.tapas.app.models.entity.Usuario;

public interface IUsuarioService {
	
	public void save(Usuario usuario);
	
	public Usuario findByUsername(String username);
}
