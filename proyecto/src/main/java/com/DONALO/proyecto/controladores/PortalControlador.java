package com.DONALO.proyecto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalControlador {
	
	@GetMapping("/")
    public String home(){
        
        return "home.html";
    }
	
	@GetMapping("/login")
    public String login(){
        
        return "login.html";
    }
    
    
}
