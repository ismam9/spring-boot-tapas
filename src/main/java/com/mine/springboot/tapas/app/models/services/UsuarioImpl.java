package com.mine.springboot.tapas.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.springboot.tapas.app.models.dao.IUsuarioDao;
import com.mine.springboot.tapas.app.models.entity.Usuario;

@Service
public class UsuarioImpl implements IUsuarioService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioDao.findByUsername(username);
	}
}
