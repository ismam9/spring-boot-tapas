package com.mine.springboot.tapas.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mine.springboot.tapas.app.models.entity.Vote;
import com.mine.springboot.tapas.app.models.services.IBarService;
import com.mine.springboot.tapas.app.models.services.ICategoriaBebidaService;
import com.mine.springboot.tapas.app.models.services.ICategoriaComidaService;
import com.mine.springboot.tapas.app.models.services.IComidaService;
import com.mine.springboot.tapas.app.models.services.ITapasService;
import com.mine.springboot.tapas.app.models.services.IUsuarioService;
import com.mine.springboot.tapas.app.models.services.JpaUserDetailsService;

@Controller
public class HomeController {

	@Autowired
	private ICategoriaComidaService categoriacomidaService;
	
	@Autowired
	private ICategoriaBebidaService categoriabebidaService;

	@GetMapping(value = {"", "/"})
	public String portada() {
		return "portada";
	}
	
	@GetMapping(value = {"/home"})
	public String tapas(Model model) {
				
		Vote votes = new Vote();

		//Usuario usuario = usuarioService.findByUsername(user.getUsername());
		model.addAttribute("votes", votes);
		
		model.addAttribute("categcomida", categoriacomidaService.findAll());
		model.addAttribute("categbebida", categoriabebidaService.findAll());
		//model.addAttribute("tapas", tapasService.findAllTapas());
		model.addAttribute("title", "El diván de las Delicias");
		return "listar-tapas";
	}
	
}
