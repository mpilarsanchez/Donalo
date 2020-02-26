package com.DONALO.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.enumeraciones.Seleccion;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;
import com.DONALO.proyecto.servicios.PublicacionServicio;
import com.DONALO.proyecto.servicios.UsuarioServicio;

@Controller
@RequestMapping("/publicacion")
public class PublicacionControlador {

	@Autowired
	PublicacionServicio publicacionServicio;
	
	@Autowired
	UsuarioServicio usuarioServicio;
	
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	
	@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
	@GetMapping("/crear")
    public String crear(){
        
        return "crear_publicacion.html";
    }
	
	
	@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
	@PostMapping("/publicar")
	 public String publicar(ModelMap modelo, @RequestParam String titulo,@RequestParam String descripcion, MultipartFile archivo, @RequestParam String seleccion) throws ErrorServicio  {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      Usuario usuario = usuarioRepositorio.buscarPorMail(auth.getName());
		
		//  idUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
//		SecurityContextHolder.getContext().SecurityContextHolder.getContext().getAuthentication().getAuthentication();
//		Object principal = .getPrincipal();
//		UserDetails userDetails = null;
//		if (principal instanceof UserDetails) {
//		  userDetails = (UserDetails) principal;
//		}
//		 usuario =  (Usuario) userDetails;
		
		//Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			publicacionServicio.altaPublicacion(titulo,descripcion, usuario, archivo, seleccion);
		} catch (Exception ex) {
			modelo.put("error", ex.getMessage());
			modelo.put("titulo", titulo);
	        modelo.put("descripcion", descripcion);
	        
			//return "redirect:/publicacion/crear?id=" + id + "&error=" + ex.getMessage();
		 return "crear_publicacion.html";
		}
		modelo.put("titulo", titulo);
        modelo.put("descripcion", descripcion);
		//return "redirect:/publicacion/detalle";
		return "redirect:/publicacion/detalle";
	}
	
	@GetMapping("/detalle")
    public String publicacion(){
        
        return "publicacion.html";
    }
	
}


//package com.DONALO.proyecto.controladores;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.DONALO.proyecto.entidades.Usuario;
//import com.DONALO.proyecto.enumeraciones.Seleccion;
//import com.DONALO.proyecto.errores.ErrorServicio;
//import com.DONALO.proyecto.servicios.PublicacionServicio;
//import com.DONALO.proyecto.servicios.UsuarioServicio;
//
//@Controller
//@RequestMapping("/publicacion")
//public class PublicacionControlador {
//
//	@Autowired
//	PublicacionServicio publicacionServicio;
//	
//	@Autowired
//	UsuarioServicio usuarioServicio;
//	
//	@GetMapping("/crear")
//    public String publicacion(){
//        
//        return "crear_publicacion.html";
//    }
//	
//	@PostMapping("/actualizar")
//	public String actualizar(@RequestParam(required = false) String id, @RequestParam String id_Usuario,
//			@RequestParam String descripcion, MultipartFile archivo,Seleccion seleccion, ModelMap modelo) throws ErrorServicio  {
//      
//		try {
//			UserDetails usuario_logueado = usuarioServicio.loadUserByUsername(id_Usuario);
//			publicacionServicio.altaPublicacion(archivo, id_Usuario, descripcion, seleccion);
//		} catch (Exception ex) {
//			modelo.put("error", ex.getMessage());
//
//			return "redirect:/publicacion/formulario?id=" + id + "&error=" + ex.getMessage();
//		}
//
//		//return "redirect:/publicacion/detalle";
//		return "redirect:/";
//	}
//}
