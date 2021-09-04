package com.springusers.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springusers.model.UsuarioModel;
import com.springusers.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	//Metodo usado para obtener los usuarios registrados en la database
	@GetMapping()
	public ArrayList<UsuarioModel> obtenerUsuarios(){
		return usuarioService.obtenerUsuarios();
	}
	
	//Metodo usado para ingresar usuarios a la database
	@PostMapping()
	public UsuarioModel guardarusuario(@RequestBody UsuarioModel usuario) {
		return this.usuarioService.guardarUsuario(usuario);
	}
	
	//Metodo usado para filtrar(buscar) usuarios por id
	@GetMapping(path = "/{id}")
	public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") long id){
		return this.usuarioService.obtenerPorId(id);
	}
	
	//Metodo usado para filtrar(buscar) usuarios por prioridad
	@GetMapping(path = "/query")
	public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
		return this.usuarioService.obtenerPrioridad(prioridad);
	}
	
	
	//Metodo usado para borrar usuarios por el id
	@DeleteMapping(path = "/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.usuarioService.eliminarUsuario(id);
		if(ok) {
			return "Se elimino correctamente" + id;
		}else {
			return "No se pudo eliminar usuario con id" + id;
		}
	}
}
