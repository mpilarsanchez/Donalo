package com.DONALO.proyecto.servicios;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.DONALO.proyecto.entidades.Foto;
import com.DONALO.proyecto.entidades.Publicacion;
import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.enumeraciones.Seleccion;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.PublicacionRepositorio;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;

@Service
public class PublicacionServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private FotoServicio fotoServicio;
	
	@Autowired
	private PublicacionRepositorio publicacionRepositorio;
	
	
	@Transactional
	public void altaPublicacion (MultipartFile archivo,String id_Usuario,String descripcion, Seleccion seleccion) throws ErrorServicio {
	
		
		Usuario usuario = usuarioRepositorio.findById(id_Usuario).get();
		validacion (id_Usuario,seleccion);
		Publicacion publicacion = new Publicacion();
		
	
		publicacion.setDescripcion(descripcion);
		publicacion.setSeleccion(seleccion);
		publicacion.setAlta(new Date());
		Foto foto = fotoServicio.guardar(archivo);
		publicacion.setFoto(foto);
		
		publicacionRepositorio.save(publicacion);
		
	}
	
	
	@Transactional
	public void validacion (String id_Usuario, Seleccion seleccion) throws ErrorServicio {
		
		if(id_Usuario==null||id_Usuario.isEmpty()) {
			throw new ErrorServicio("El usuario no puede ser nulo o estar vacío");
		}
		
		if(seleccion == null) {
			throw new ErrorServicio("La seleccion del objeto no puede ser nula");
		}
	}


		
	
	
	@Transactional
	public void modificacionPublicacion (MultipartFile archivo,String id_Usuario,String descripcion, Seleccion seleccion) throws ErrorServicio{
		validacion (id_Usuario,seleccion);
		
		Optional<Publicacion> respuesta = publicacionRepositorio.findById(id_Usuario);
		
		 if(respuesta.isPresent()){
		        Publicacion publicacion = respuesta.get();
		        if ( publicacion.getId_Usuario().getId().equals(id_Usuario)){
		        	publicacion.setDescripcion(descripcion);
		    		publicacion.setSeleccion(seleccion);
		        
		        String idFoto =null;
		        if(publicacion.getFoto()!=null){
		            idFoto=publicacion.getFoto().getId();
		        }
		        
		        Foto foto = fotoServicio.actualizar(idFoto, archivo);
		        publicacion.setFoto(foto);
		        
		        publicacionRepositorio.save(publicacion);
		     }else {
		    throw new ErrorServicio("No existe el producto con el identificador solicitado ");
		   }}else{
		    throw new ErrorServicio("No tiene permisos suficientes para realizar la operacion");
		    
		 }
	}
	
	  @Transactional 
	    public void eliminar (String id_Usuario,String id)throws ErrorServicio{
	         Optional<Publicacion> respuesta= publicacionRepositorio.findById(id);
	        
	        if(respuesta.isPresent()){
	          Publicacion publicacion = respuesta.get();
	          if (publicacion.getId_Usuario().getId().equals(id_Usuario)){
	            publicacion.setBaja(new Date());
	             publicacionRepositorio.save(publicacion);
	          }
	    }else{
	        throw new ErrorServicio("No existe un producto con el identificador solicitado ");
	        }

	  
	    }
	
	
	
	

	  
	  
	  
}