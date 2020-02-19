package com.DONALO.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DONALO.proyecto.servicios.UsuarioServicio;

@Controller
@RequestMapping("/fomulario")
public class RegistroControlador {

@Autowired
private UsuarioServicio usuarioservicio;



@GetMapping
public String registrar( @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave, @RequestParam String clave1 ) {
	
	
	try {
		usuarioservicio.UsuarioAlta(nombre, apellido, mail, clave, clave1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
	return "formulario.html";
}

	

}
