package com.mine.springboot.tapas.app.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mine.springboot.tapas.app.models.entity.Bar;
import com.mine.springboot.tapas.app.models.entity.Tapas;
import com.mine.springboot.tapas.app.models.entity.Usuario;
import com.mine.springboot.tapas.app.models.entity.Vote;
import com.mine.springboot.tapas.app.models.services.ITapasService;
import com.mine.springboot.tapas.app.models.services.IUsuarioService;
import com.mine.springboot.tapas.app.models.services.IVoteService;


@RestController
public class VoteController {
	
	@Autowired
	private IVoteService voteService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ITapasService tapaService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@PostMapping(value = "/votecreate")
	public String guardar(@Valid Vote vote, BindingResult result, Model model,
			@RequestParam(name = "tapa", required = true) Tapas tapa,
			RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
	        for(ObjectError error : errors) {
	            System.out.println("This is the error: " +error);
	        }
			return "redirect:/home";
		}
		
		//String mensajeFlash = (vote.getId() != null) ? "voto quitado con con exito" : "votado con exito!";
		
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails)auth.getPrincipal();
		Usuario usuario = (Usuario)usuarioService.findByUsername(userDetails.getUsername());
		
		//Tapas tapa = tapaService.findTapaById(Long.parseLong(tapaid));
		
		vote.setTapa(tapa);
		vote.setUsuario(usuario);

		voteService.save(vote);
		status.setComplete();
		//flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:/home";
	}
	
	@GetMapping(value = "/votedelete/{tapaId}")
	public String eliminar(@PathVariable(value = "tapaId") Long tapaId, RedirectAttributes flash) {
		
		//Tapas tapa = tapaService.findTapaById(tapaId);
		
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails)auth.getPrincipal();
		Usuario usuario = (Usuario)usuarioService.findByUsername(userDetails.getUsername());
		Long userId = usuario.getId();
		log.debug("Usuario id:", userId);
		
		if (userId != null && tapaId != null) {

			Vote vote = voteService.selectVoteByUserAndTapa(userId, tapaId);
			log.debug("vote:", vote.getId());
			voteService.delete(vote.getId());
			flash.addFlashAttribute("success", "Voto eliminado con exito");
			
			return "redirect:/home";
		}
		
		flash.addFlashAttribute("error", "El bar no existe en la base de datos");
		return "redirect:/bares/";
	}
}
