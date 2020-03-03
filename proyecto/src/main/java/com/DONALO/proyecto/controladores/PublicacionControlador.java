package com.DONALO.proyecto.controladores;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DONALO.proyecto.entidades.Publicacion;
import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;
import com.DONALO.proyecto.servicios.FotoServicio;
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
	
	@Autowired
	FotoServicio fotoServicio;
	
	
	
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

		return "redirect:/publicacion/detalle";
	}
	
	
	@GetMapping("/detalle")
    public String publicacion(){
        
        return "publicacion.html";
    }
	


	@GetMapping("/mensaje")
	public String mensaje(ModelMap modelo ) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      Usuario usuario = usuarioRepositorio.buscarPorMail(auth.getName());
	    modelo.put("usuario", usuario);
		
		return "mensaje.html";
	}
	
	
	@GetMapping("/publicaciones")
	public String publicaciones(@RequestParam(required = false) String q, @RequestParam(required = false) String error, ModelMap modelo) {
			
		     List<Publicacion> publicaciones ;
			 
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		      Usuario usuario = usuarioRepositorio.buscarPorMail(auth.getName());
		      
			 if (q != null) {
		            publicaciones = publicacionServicio.buscarPublicacion(q);
		        } else {
		        	publicaciones = publicacionServicio.buscarPublicacion();
		        }
		        
		        modelo.put("q", q);
		        modelo.put("publicaciones", publicaciones);
		        modelo.put("usuario", usuario);
		        modelo.put("error", error);
           
		        return "publicaciones.html";
	}

	
	@GetMapping("/misPublicaciones")
	public String misPublicaciones(@RequestParam(required = false) String error, ModelMap modelo) {

		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      Usuario usuario = usuarioRepositorio.buscarPorMail(auth.getName());	 
		  
	      List<Publicacion> publicaciones = publicacionServicio.buscarPublicacionPorUsuario(usuario.getId());
	      modelo.put("publicaciones", publicaciones);
          modelo.put("error", error);
	      
//	      if ( publicaciones != null) {
//	    	   modelo.put("publicaciones", publicaciones);
//		        modelo.put("error", error);
//		           
//		        } else {
//		        	System.out.println("Aun no tiene ninguna publicación");
//		        }
//		       
             return "publicaciones_usuario.html";
	}
}


