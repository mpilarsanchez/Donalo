package com.DONALO.proyecto.servicios;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DONALO.proyecto.entidades.Mensaje;
import com.DONALO.proyecto.entidades.Publicacion;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.MensajeRepositorio;
import com.DONALO.proyecto.repositorios.PublicacionRepositorio;


@Service
public class MensajesServicio {
	@Autowired
	private MailNotificacionServicio notificacionServicio;
	@Autowired
	private PublicacionRepositorio publicacionRepositorio;
	@Autowired
	private MensajeRepositorio mensajeRepositorio;

	@Transactional
	public void mensaje(String idUsuario, String id_Usuario2, String contenido, Date fecha) throws ErrorServicio {

		Mensaje mensaje = new Mensaje();
		mensaje.setFecha(new Date());

		Optional<Publicacion> respuesta = publicacionRepositorio.findById(idUsuario);

		if (respuesta.isPresent()) {

			Publicacion publicacion1 = respuesta.get();
			if (publicacion1.getUsuario().getId().equals(idUsuario)) {
				mensaje.setContenido(idUsuario);
			} else {
				throw new ErrorServicio("No tiene permisos para realizar la operacion solicitada");
			}
		} else {
			throw new ErrorServicio("No existe una publicacion identificada con ese indentificador");
		}

		Optional<Publicacion> respuesta2 = publicacionRepositorio.findById(id_Usuario2);

		if (respuesta.isPresent()) {

			Publicacion publicacion2 = respuesta.get();
			mensaje.setContenido(id_Usuario2);
			if (publicacion2.getUsuario().getId().equals(id_Usuario2)) {
				mensaje.setContenido(id_Usuario2);
				notificacionServicio.enviar("Tu mensaje se ha enviado ", "Donalo.com",
						publicacion2.getUsuario().getMail());
			} else {
				throw new ErrorServicio("No tiene permisos para realizar la operacion solicitada");
			}
		} else {
			throw new ErrorServicio("No existe una mascota identificada con ese indentificador");
		}

		if (idUsuario.equals(id_Usuario2)) {

			throw new ErrorServicio("No puede votarse a si mismo");
		}

		mensajeRepositorio.save(mensaje);

	}

	@Transactional
	public void responder(String idUsuario, String mensaje) throws ErrorServicio {
		Optional<Mensaje> respuesta = mensajeRepositorio.findById(idUsuario);

		if (respuesta.isPresent()) {
			Mensaje mensaje1 = respuesta.get();
			mensaje1.setFecha(new Date());

			if (mensaje1.getId_Usuario2().getNombre().equals(idUsuario)) {
				notificacionServicio.enviar("Tu mensaje fue correspondido ", "Donalo.com",
						mensaje1.getId_Usuario1().getNombre());
				mensajeRepositorio.save(mensaje1);
			} else {
				throw new ErrorServicio("no tiene Permiso para realizar la operacion");
			}

		} else {
			throw new ErrorServicio("No existe el mensaje solicitado");
		}

	}

}

