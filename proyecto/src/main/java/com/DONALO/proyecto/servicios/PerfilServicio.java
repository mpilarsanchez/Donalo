package com.DONALO.proyecto.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DONALO.proyecto.repositorios.UsuarioRepositorio;

@Service
public class PerfilServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	

}
