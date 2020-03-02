package com.DONALO.proyecto.controladores;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DONALO.proyecto.servicios.FotoServicio;

@Controller
@RequestMapping("/")
public class FotoControlador {
 
	@Autowired
	FotoServicio fotoServicio;
//
//	@GetMapping("/img/{id}")
//	public void getImage(@PathVariable("id") Long id, HttpServletResponse response) {
//	        fotoServicio.writeImageToRespose(id, response);
//	}
}
