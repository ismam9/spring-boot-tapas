package com.mine.springboot.tapas.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.springboot.tapas.app.models.dao.IBebidaDao;

@Service
public class BebidaImpl {
	
	@Autowired
	private IBebidaDao bebidaDao;
}
