package com.DONALO.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.servicios.UsuarioServicio;
@RequestMapping("/editar_perfil")
@Controller
public class EditarperfilControlador {



@Autowired
private UsuarioServicio servicio;
@GetMapping("/")
public String cargar(ModelMap modelo, @AuthenticationPrincipal Usuario usuario) {

	modelo.put("nombreviejo", usuario.getNombre());
	modelo.put("apellidoviejo", usuario.getNombre());
	modelo.put("mailviejo", usuario.getNombre());
	
	return "editar_perfil.html";
	
}


@PostMapping("/editar")
public String editar(@AuthenticationPrincipal Usuario usuario, ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2, MultipartFile archivo){
	
	   try {
	        servicio.modificacionUsuario(archivo, usuario.getId() , nombre, apellido, mail, clave1, clave2);
	    } catch (ErrorServicio ex) {
	       
	        modelo.put("error", ex.getMessage());
	        modelo.put("nombre", nombre);
	        modelo.put("apellido", apellido);
	        modelo.put("mail", mail);
	        modelo.put("clave1", clave1);
	        modelo.put("clave2", clave2);
	        
	        return "registro.html";
	    }
	    
	   return "perfil.html";
	}
	
	
	
	
	




}
	
	
	
	