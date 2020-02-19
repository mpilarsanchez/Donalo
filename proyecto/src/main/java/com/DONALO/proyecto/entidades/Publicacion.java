package com.DONALO.proyecto.entidades;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.DONALO.proyecto.enumeraciones.Seleccion;

import lombok.Getter;
import lombok.Setter;
//@Getter
//@Setter
@Entity 
public class Publicacion {

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	 @OneToOne
	 private Foto foto;
	 
	 @ManyToOne
	 private Usuario id_Usuario;
		

	private String descripcion;
	
    @Enumerated(EnumType.STRING)
	private Seleccion seleccion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Usuario getId_Usuario() {
		return id_Usuario;
	}

	public void setId_Usuario(Usuario id_Usuario) {
		this.id_Usuario = id_Usuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Seleccion getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}
    
    
    
    
    
    
    
    
    
	 
}
