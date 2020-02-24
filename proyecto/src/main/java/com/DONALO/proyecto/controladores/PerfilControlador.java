package com.DONALO.proyecto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilControlador {

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
