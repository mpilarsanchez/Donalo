package com.DONALO.proyecto.servicios;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DONALO.proyecto.entidades.Foto;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.FotoRepositorio;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;
@Service
public class FotoServicio {

	@Autowired
	private FotoRepositorio fotoRepositorio;
	
	@Transactional 
	public Foto guardar(MultipartFile archivo) throws ErrorServicio{
		if(archivo!= null){
	        try{
	        Foto foto = new Foto();
	        foto.setMime(archivo.getContentType());
	        foto.setNombre(archivo.getName());
	        foto.setContenido(archivo.getBytes());
	        
	      return fotoRepositorio.save(foto);
	        }catch (Exception e ){
	            System.out.println(e.getMessage());
	        }
	    }
	        return null;	
	}
	
	@Transactional  
    public Foto  actualizar (String idFoto,MultipartFile archivo) throws ErrorServicio{
        if(archivo!= null){
        try{
        Foto foto = new Foto();
        
        if(idFoto!=null){
        	// El Optional lo importa desde aqui ya que SpringTools no me deja importa esa biblioteca de optional
       java.util.Optional<Foto> respuesta = fotoRepositorio.findById(idFoto);
         if(respuesta.isPresent()) {
        	 foto=respuesta.get();
            }
        }
        foto.setMime(archivo.getContentType());
        foto.setNombre(archivo.getName());
        foto.setContenido(archivo.getBytes());
        
      return fotoRepositorio.save(foto);
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }
        return null;
           
    }

	
//		   public void writeImageToRespose(Long id, HttpServletResponse response) {
//		   
//			   //store image in browser cache
//		        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//		        response.setHeader("Cache-Control", "max-age=2628000");
//
//		        //obtaining bytes from DB
//		        byte[] imageData = fotoRepositorio.getPhotoById(id);
//
//		        //Some conversion
//		        //Maybe to base64 string or something else
//		        //Pay attention to encoding (UTF-8, etc)
//		       
//		        //write result to http response
//		        try (OutputStream out = response.getOutputStream()) {
//		            out.write(convertedStringBytes);
//		        }
//          }	
	
}

