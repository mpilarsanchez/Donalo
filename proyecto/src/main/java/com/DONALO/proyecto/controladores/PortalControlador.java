package com.DONALO.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;



@Controller
public class PortalControlador {

@Autowired
private UsuarioRepositorio repositorio;
	
	
	@GetMapping("/")
    public String home(){
        
        return "home.html";
    }
	
	@GetMapping("/login")
    public String login(){
        
        return "login.html";
    }
    
   @GetMapping("/perfil")
    public String perfil(ModelMap modelo) {
    	
    	
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   
	
   	 
   	 modelo.put("nombre", auth.getName());
   	 
   	 Usuario usuario= repositorio.findByUsername(auth.getName());
   	 
   	 
   	 modelo.put("apellido", usuario.getApellido());
   	 modelo.put("mail", usuario.getMail());
   	 
   	 
   	 
   	 
   
   	
   	
   	
   	
   	
   	return "perfil.html";
   	
    	
    	
    	
    	
    
    	
    }
}
