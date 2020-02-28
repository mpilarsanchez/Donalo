package com.DONALO.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DONALO.proyecto.entidades.Publicacion;

@Repository("publicacionRepositorio")
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String> {

}
