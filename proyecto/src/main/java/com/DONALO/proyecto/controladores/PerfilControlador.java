package com.DONALO.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.servicios.PerfilServicio;
@RequestMapping("/perfil")
@Controller
public class PerfilControlador {


@GetMapping("/")
public String perfil1(ModelMap modelo, @AuthenticationPrincipal Usuario usuario) {
	
	
	
	
	
	
	
	 
	 modelo.put("nombre", usuario.getNombre());
     modelo.put("apellido", usuario.getApellido());
     modelo.put("mail", usuario.getMail());
    
	
	
	
	
	
	return "perfil.html";
	
	
}



	
	
	
	
}
