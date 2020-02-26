package com.DONALO.proyecto.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.DONALO.proyecto.entidades.Usuario;

@Controller
public class PerfilControlador {

@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@GetMapping("/perfil")
public String perfil() {	 
	
	return "perfil.html";
	
}

@GetMapping("/editar_perfil")
public String editar_perfil() {	 
	return "editar_perfil.html";	
}

@GetMapping("/publicion/formulario")
public String publicacion() {	 
	return "crear_publicacion.html";	
}

	
	
	
	
}
