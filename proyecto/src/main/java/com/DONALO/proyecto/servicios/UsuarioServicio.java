package com.DONALO.proyecto.servicios;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DONALO.proyecto.entidades.Foto;
import com.DONALO.proyecto.entidades.Usuario;
import com.DONALO.proyecto.errores.ErrorServicio;
import com.DONALO.proyecto.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private FotoServicio fotoServicio;

	@Transactional
	public void altaUsuario(String nombre, String apellido, String mail, String clave, String clave2,
			MultipartFile archivo) throws ErrorServicio {

		validar(nombre, apellido, mail, clave, clave2);

		Usuario usuario = new Usuario();

		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setMail(mail);

		// Seguridad de la clave
		String encriptada = new BCryptPasswordEncoder().encode(clave);
		usuario.setClave(encriptada);
		usuario.setAlta(new Date());

		Foto foto = fotoServicio.guardar(archivo);
		usuario.setFoto(foto);

		usuarioRepositorio.save(usuario);

	}

	@Transactional
	public void validar(String nombre, String apellido, String mail, String clave, String clave2) throws ErrorServicio {

		if (nombre.isEmpty() || nombre == null) {
			throw new ErrorServicio("El nombre del usuario no puede estar vacío o ser nulo");
		}

		if (apellido.isEmpty() || apellido == null) {
			throw new ErrorServicio("El apellido del usuario no puede estar vacío o ser nulo");
		}

		if (mail.isEmpty() || mail == null) {
			throw new ErrorServicio("El mail del usuario no puede estar vacío o ser nulo");
		}

		if (clave.isEmpty() || clave == null || clave.length() <= 6) {
			throw new ErrorServicio(
					"La clave del usuario no puede estar vacío o ser nulo; y tiene que tener más de 6 dígitos");
		}

		if (!clave.equals(clave2)) {
			throw new ErrorServicio("Las claves deben ser iguales");
		}

	}

	@Transactional
	public void modificacionUsuario(MultipartFile archivo, String id, String nombre, String apellido, String mail,
			String clave, String clave2) throws ErrorServicio {
		validar(nombre, apellido, mail, clave, clave2);

		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

		if (respuesta.isPresent()) {
			Usuario usuario = respuesta.get();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setMail(mail);
			String encriptada = new BCryptPasswordEncoder().encode(clave);
			usuario.setClave(encriptada);

			usuarioRepositorio.save(usuario);
			String idFoto = null;
			if (usuario.getFoto() != null) {
				idFoto = usuario.getFoto().getId();
			}

			Foto foto = fotoServicio.actualizar(idFoto, archivo);
			usuario.setFoto(foto);
			usuarioRepositorio.save(usuario);

		} else {
			throw new ErrorServicio("No se encontro el usuario solicitado");
		}

	}

	@Transactional
	public void bajaUsuario(String id) throws ErrorServicio {

		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

		if (respuesta.isPresent()) {

			Usuario usuario = respuesta.get();
			usuarioRepositorio.deleteById(id);
		} else {
			throw new ErrorServicio("El autor no pudo ser eliminado exitosamente");
		}

	}

}
