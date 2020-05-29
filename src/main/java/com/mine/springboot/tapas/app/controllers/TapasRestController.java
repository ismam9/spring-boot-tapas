package com.mine.springboot.tapas.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mine.springboot.tapas.app.models.entity.Tapas;
import com.mine.springboot.tapas.app.models.entity.Usuario;
import com.mine.springboot.tapas.app.models.services.ITapasService;
import com.mine.springboot.tapas.app.models.services.IUsuarioService;
import com.mine.springboot.tapas.app.models.services.IVoteService;

@RestController
public class TapasRestController {
	
	@Autowired
	private ITapasService tapasService;
	
	@Autowired
	private IVoteService voteService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value = "/isvoted", method=RequestMethod.GET)
	public @ResponseBody List<Boolean> isvoted() {
		
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails)auth.getPrincipal();
		Usuario usuario = (Usuario)usuarioService.findByUsername(userDetails.getUsername());
		Long id = usuario.getId();
		
		List<Tapas> tapas = tapasService.findAllTapas();
		List<Boolean> listavotos = new ArrayList<Boolean>();
		
		for (Tapas tapa : tapas) {
			if (voteService.selectVoteByUserAndTapa(id, tapa.getId()) != null) {
				listavotos.add(true);
			}else {
				listavotos.add(false);
			}
		}
		return listavotos;
		
	}
	
	@RequestMapping(value = "/tapast", method=RequestMethod.GET)
	public List<Tapas> getAllTapas(){
		return tapasService.findAllTapas();
	}
    @RequestMapping(value = "/tapat/{id}", method = RequestMethod.GET)
	public Tapas getTapaById(@PathVariable("id") long id){
		return tapasService.findTapaById(id);
	}
}
