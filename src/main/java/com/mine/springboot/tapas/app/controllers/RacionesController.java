package com.mine.springboot.tapas.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mine.springboot.tapas.app.models.entity.Bar;
import com.mine.springboot.tapas.app.models.entity.Bebida;
import com.mine.springboot.tapas.app.models.entity.CategoriaBebida;
import com.mine.springboot.tapas.app.models.entity.CategoriaComida;
import com.mine.springboot.tapas.app.models.entity.Comida;
import com.mine.springboot.tapas.app.models.entity.Raciones;
import com.mine.springboot.tapas.app.models.services.IBarService;
import com.mine.springboot.tapas.app.models.services.IRacionesService;

@Controller
@RequestMapping(value = "/raciones")
@SessionAttributes(value = "raciones")
public class RacionesController {
	
	@Autowired
	private IRacionesService racionesService;
	
	@Autowired
	private IBarService barService;
	
	@Secured({"ROLE_ADMIN", "ROLE_BAR"})
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id,
			RedirectAttributes flash) {
		
		Raciones racion = racionesService.findRacionById(id);
		
		if (racion != null) {
			racionesService.deleteRacion(id);
			flash.addFlashAttribute("success", "Tapa eliminada con exito");
			
			return "redirect:/bares/ver-bar/" + racion.getBar().getId();
		}
		
		flash.addFlashAttribute("error", "La racion no existe en la base de datos");
		return "redirect:/bares/";
		
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_BAR"})
	@GetMapping("/form/{barId}")
	public String crear(@PathVariable(value = "barId") Long barId,
			Map<String, Object> model,
			RedirectAttributes flash) {
		
		Bar bar = barService.findOne(barId);
		
		if(bar == null) {
			flash.addFlashAttribute("error", "El bar no existe");
		}
		
		Raciones racion = new Raciones();
		Bebida bebida = new Bebida();
		Comida comida = new Comida();
		
		racion.setBar(bar);
				
		model.put("bebida", bebida);
		model.put("comida", comida);
		model.put("racion", racion);
		model.put("bar", bar);
		model.put("title", "Crear una Tapa");
		
		return "raciones/form-racion";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_BAR"})
	@GetMapping("/editform/{tapaId}")
	public String editar(@PathVariable(value = "racionId") Long racionId, 
			Map<String, Object> model, RedirectAttributes flash) {
		
		Raciones racion = null;
			
		if (racionId > 0) {
			racion = racionesService.findRacionById(racionId);
			if (racion == null) {
				flash.addFlashAttribute("error", "El id de la racion no existe ");
				return "redirect:/bares";
			}
		} else {
			flash.addFlashAttribute("error", "el id de la racion no puede");
			return "redirect:/bares";
		}
		
		Bebida bebida = new Bebida();
		Comida comida = new Comida();
		Bar bar = racion.getBar();
				
		model.put("bebida", bebida);
		model.put("comida", comida);
		model.put("racion", racion);
		model.put("bar", bar);
		model.put("title", "Editar el bar");
		
		return "tapas/form-tapa";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_BAR"})
	@PostMapping("/form")
	public String guardar(@Valid Raciones racion, @Valid Comida comida, @Valid Bebida bebida,
			BindingResult result,
			Model model,
			@RequestParam(name = "comidanombre", required = false) String comidanombre,
			@RequestParam(name = "bebidanombre", required = false) String bebidanombre,
			@RequestParam(name = "categoriacomida", required = false) CategoriaComida categoriacomida,
			@RequestParam(name = "categoriabebida", required = false) CategoriaBebida categoriabebida,
			RedirectAttributes flash,
			SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Crear Racion");
			return "raciones/form-racion";
		}
		
		
		comida.setCategoriacomida(categoriacomida);
		comida.setNombre(comidanombre);
		bebida.setCategoriabebida(categoriabebida);
		bebida.setNombre(bebidanombre);
		
		racion.setComida(comida);
		racion.setBebida(bebida);
		
		String mensajeFlash = (racion.getId() != null) ? "racion editado con exito" : "racion creado con exito!";
		
		racionesService.save(racion);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:/bares/ver-bar/" + racion.getBar().getId();
		
	}

}
