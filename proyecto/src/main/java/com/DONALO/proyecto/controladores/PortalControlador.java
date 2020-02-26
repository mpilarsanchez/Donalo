package com.DONALO.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DONALO.proyecto.servicios.UsuarioServicio;





@Controller
public class PortalControlador {

	 @Autowired
	    private UsuarioServicio usuarioServicio;
	
	@GetMapping("/")
    public String home(){
        
        return "home.html";
    }
//	
//	@GetMapping("/login")
//    public String login(){
//        
//        return "login.html";
//    }
    
	 @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
	    @GetMapping("/inicio")
	    public String inicio(){
	        return "perfil.html";
	    }
	    
	    @GetMapping("/login")
	    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model){
	        if(error != null){
	            model.put("error", "Nombre de usuario o clave incorrectos.");
	        }
	        
	        if(logout != null){
	            model.put("logout", "Ha salido correctamente de la plataforma.");
	        }
	        
	        return "login.html";
	    }
    
    	
    }

