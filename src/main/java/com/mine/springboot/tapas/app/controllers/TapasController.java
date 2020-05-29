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
import com.mine.springboot.tapas.app.models.entity.Tapas;
import com.mine.springboot.tapas.app.models.services.IBarService;
import com.mine.springboot.tapas.app.models.services.ITapasService;

@Controller
@RequestMapping(value = "/tapas")
@SessionAttributes(value = "tapas")
public class TapasController {
	
	@Autowired
	private ITapasService tapasService;
	
	@Autowired
	private IBarService barService;
	
	@GetMapping("/ver-tapa/{id}")
	public String verTapa(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Tapas tapa = tapasService.findOneTapa(id);

		if (tapa == null) {
			flash.addFlashAttribute("error", "La tapa no existe en la base de datos");
			return "redirect:/";
		}

		model.put("tapa", tapa);
		model.put("title", "Tapa: " + tapa.getNombre());

		return "ver-tapa";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_BAR"})
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id,
			RedirectAttributes flash) {
		
		Tapas tapa = tapasService.findTapaById(id);
		
		if (tapa != null) {
			tapasService.deleteTapa(id);
			flash.addFlashAttribute("success", "Tapa eliminada con exito");
			
			return "redirect:/bares/ver-bar/" + tapa.getBar().getId();
		}
		
		flash.addFlashAttribute("error", "El beat no existe en la base de datos");
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
		
		Tapas tapa = new Tapas();
		Bebida bebida = new Bebida();
		Comida comida = new Comida();
		
		tapa.setBar(bar);
				
		model.put("bebida", bebida);
		model.put("comida", comida);
		model.put("tapa", tapa);
		model.put("bar", bar);
		model.put("title", "Crear una Tapa");
		
		return "tapas/form-tapa";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_BAR"})
	@GetMapping("/editform/{tapaId}")
	public String editar(@PathVariable(value = "tapaId") Long tapaId, 
			Map<String, Object> model, RedirectAttributes flash) {
		
		Tapas tapa = null;

		if (tapaId > 0) {
			tapa = tapasService.findTapaById(tapaId);
			if (tapa == null) {
				flash.addFlashAttribute("error", "El id de la tapa no existe ");
				return "redirect:/bares";
			}
		} else {
			flash.addFlashAttribute("error", "el id de la tapa no puede");
			return "redirect:/bares";
		}
		
		Bebida bebida = new Bebida();
		Comida comida = new Comida();
		Bar bar = tapa.getBar();
				
		model.put("bebida", bebida);
		model.put("comida", comida);
		model.put("tapa", tapa);
		model.put("bar", bar);
		model.put("title", "Editar la Tapa");
		
		return "tapas/form-tapa";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_BAR"})
	@PostMapping("/form")
	public String guardar(@Valid Tapas tapa, @Valid Comida comida, @Valid Bebida bebida,
			BindingResult result,
			Model model,
			@RequestParam(name = "comidanombre", required = false) String comidanombre,
			@RequestParam(name = "bebidanombre", required = false) String bebidanombre,
			@RequestParam(name = "categoriacomida", required = false) CategoriaComida categoriacomida,
			@RequestParam(name = "categoriabebida", required = false) CategoriaBebida categoriabebida,
			RedirectAttributes flash,
			SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Crear tapa");
			return "tapas/form-tapa";
		}
		
		
		comida.setCategoriacomida(categoriacomida);
		comida.setNombre(comidanombre);
		bebida.setCategoriabebida(categoriabebida);
		bebida.setNombre(bebidanombre);
		
		tapa.setComida(comida);
		tapa.setBebida(bebida);
		
		String mensajeFlash = (tapa.getId() != null) ? "tapa editado con exito" : "tapa creado con exito!";
		
		tapasService.save(tapa);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:/bares/ver-bar/" + tapa.getBar().getId();
		
	}
}
