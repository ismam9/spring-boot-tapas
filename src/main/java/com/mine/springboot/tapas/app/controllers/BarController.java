package com.mine.springboot.tapas.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mine.springboot.tapas.app.models.entity.Bar;
import com.mine.springboot.tapas.app.models.entity.Rol;
import com.mine.springboot.tapas.app.models.entity.Usuario;
import com.mine.springboot.tapas.app.models.services.IBarService;
import com.mine.springboot.tapas.app.models.services.IUploadFileService;

@Controller
@RequestMapping(value = "/bares")
@SessionAttributes(value = "bares")
public class BarController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IBarService barService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping(value = "/uploads-bares/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() +"\"")
				.body(recurso);
	}
	
	@GetMapping(value = {"", "/"})
	public String tapas(Model model) {
		model.addAttribute("title", "El mercado de las tapas");
		model.addAttribute("bares", barService.findAll());
		
		return "listar-bares-all";
	}
	
	@GetMapping("/ver-bar/{id}")
	public String verTapa(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Bar bar = barService.findOne(id);

		if (bar == null) {
			flash.addFlashAttribute("error", "El bar no existe en la base de datos");
			return "redirect:/bares";
		}

		model.put("bar", bar);
		model.put("title", "Contenido del bar: " + bar.getNombre());

		return "ver-bar";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_BAR"})
	@GetMapping("/form")
	public String crear(Map<String, Object> model, RedirectAttributes flash) {
		
		Bar bar = new Bar();
		Usuario usuario = new Usuario();
		
		model.put("usuario", usuario);
		model.put("bar", bar);
		model.put("title", "Creacion de un nuevo bar");
		
		return "bar/form-bar";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_BAR"})
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Bar bar = null;
		
		if (id > 0) {
			bar = barService.findOne(id);
			if (bar == null) {
				flash.addFlashAttribute("error", "El id del bar no exist ");
				return "redirect:/bares";
			}
		} else {
			flash.addFlashAttribute("error", "el id delcliente no puede ser cero");
			return "redirect:/bares";
		}
		
		Usuario usuario = new Usuario();
		
		model.put("usuario", usuario);
		model.put("bar", bar);
		model.put("title", "Editar el bar");
		
		return "bar/form-bar";
	}
	

	@PostMapping(value = "/form")
	public String guardar(@Valid Bar bar, @Valid Usuario usuario, BindingResult result, Model model,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password,
			@RequestParam("file") MultipartFile[] files,
			RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Creacion de un nuevo productor");
			return "bar/form-bar";
		}
		
		if (files.length > 0) {

			if (bar.getId() != null && bar.getId() > 0 && bar.getFotonif() != null
					&& bar.getFotonif().length() > 0 && bar.getFoto() != null
					&& bar.getFoto().length() > 0) {

				uploadFileService.delete(bar.getFoto());

			}

			String uniqueFilename = null;
			String uniqueFilenamea = null;

			try {
				uniqueFilename = uploadFileService.copy(files[0]);
				uniqueFilenamea = uploadFileService.copy(files[1]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "has subido correctamente '" + files[0].getOriginalFilename() + files[1].getOriginalFilename() + "'");

			bar.setFoto(uniqueFilename);
			bar.setFotonif(uniqueFilenamea);
		}
		
		String mensajeFlash = (bar.getId() != null) ? "bar editado con exito" : "bar creado con exito!";
		
		Rol rol = new Rol();
		rol.setAuthority("ROLE_USER");
		rol.setAuthority("ROLE_BAR");
		
		usuario.setUsername(username);
		usuario.setPassword(passwordEncoder.encode(password));
		usuario.setRoles(Arrays.asList(rol));
		usuario.setEnabled(true);
		
		bar.setUsers(usuario);

		barService.save(bar);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:/bares/";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_BAR"})
	@GetMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		Bar bar = barService.findOne(id);
		
		if (bar != null) {

			barService.delete(id);
			flash.addFlashAttribute("success", "Bar eliminado con exito");
			
			if (uploadFileService.delete(bar.getFoto()) && uploadFileService.delete(bar.getFotonif())) {
				flash.addFlashAttribute("info", "Foto: " + bar.getFoto() + "eliminada con exito y foto NIF: + " + bar.getFotonif() + "eliminada con exito");
			}
			return "redirect:/bares/";
		}
		
		flash.addFlashAttribute("error", "El bar no existe en la base de datos");
		return "redirect:/bares/";
	}

}
