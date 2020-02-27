package com.DONALO.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;
import com.DONALO.proyecto.servicios.UsuarioServicio;

@Controller
public class PerfilControlador {

@Autowired
private UsuarioRepositorio repo;

@Autowired
private UsuarioServicio usuarioServicio;
	
	
	
	
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@GetMapping("/perfil")
public String perfil(ModelMap model) {	 
	

	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  Usuario usuario = repo.buscarPorMail(auth.getName());
	
	model.put("nombre", usuario.getNombre());
	model.put("apellido", usuario.getApellido());
	model.put("mail", usuario.getMail());
	
	return "perfil.html";
	
}

//@GetMapping("/editar_perfil")
//public String editar_perfil(ModelMap modelo) {	 
//	
//	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//     Usuario usuario = repo.buscarPorMail(auth.getName());
//	
//	modelo.put("nombreviejo", usuario.getNombre());
//	modelo.put("apellidoviejo", usuario.getApellido());
//	modelo.put("mailviejo", usuario.getMail());
//	
//	
//	
//	
//	
//	
//	return "editar_perfil.html";	
//}

@GetMapping("/publicion/formulario")
public String publicacion() {	 
	return "crear_publicacion.html";	
}


//@PostMapping("editar")
//public String editar( ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2, MultipartFile archivo){
//	
//	System.out.println("Entre al metodo bajo mapping editar");
//	
//	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//   Usuario usuario = repo.buscarPorMail(auth.getName());
//	
//	
//	   try {
//	        usuarioServicio.modificacionUsuario(archivo, usuario.getId() , nombre, apellido, mail, clave1, clave2);
//	    } catch (ErrorServicio ex) {
//	    	System.out.println("________________________________");
//	    	System.out.println("ERROR ACA");
//	    	ex.printStackTrace();
//	        modelo.put("error", ex.getMessage());
//	        modelo.put("nombre", nombre);
//	        modelo.put("apellido", apellido);
//	        modelo.put("mail", mail);
//	        modelo.put("clave1", clave1);
//	        modelo.put("clave2", clave2);
//	      
//	        return "editar_perfil.html";
//	    }
//	    
//	   return "perfil.html";
//	}
	

	
	
	
}
