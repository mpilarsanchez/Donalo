package com.DONALO.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DONALO.proyecto.entidades.Mensaje;

@Repository("mensajeRepositorio")
public interface MensajeRepositorio extends JpaRepository<Mensaje, String> {

}
