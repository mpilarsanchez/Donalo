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
@Getter
@Setter
@Entity 
public class Publicacion {

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	 @OneToOne
	 private Foto foto;
	 
	 @ManyToOne
	 private Usuario id_Usuario1;
		
	@ManyToOne
	private Usuario id_Usuario2;
		
	private String descripcion;
	
    @Enumerated(EnumType.STRING)
	private Seleccion seleccion;
	 
}
