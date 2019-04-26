package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.model.Persona;
import com.mitocode.service.IPersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService service;
	
	
	
	
	@GetMapping
	public ResponseEntity <List<Persona>> mostrar() {
		 List<Persona> pacientes = service.listar();
		return new ResponseEntity<List<Persona>>(pacientes,HttpStatus.OK);
	}
	
	
	@GetMapping//(produces = "application/xml")
	@RequestMapping("/{id}")
	public ResponseEntity<Persona> listarPorId(@PathVariable("id") Integer idPersona) {
		Persona pac= service.leer(idPersona);
		return new ResponseEntity<Persona>(pac,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar (@Valid @RequestBody Persona paciente) {
		Persona pac= service.registrar(paciente);
		// crear url para enviar por el response y mostrar en el header
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getIdPersona()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar (@Valid @RequestBody Persona per) {
		Persona pac= service.modificar(per);
		return new ResponseEntity<Object>(pac,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  eliminar (@PathVariable("id")Integer IdPersona) {
		Persona pac= service.leer(IdPersona);
		return new ResponseEntity<Object>(pac,HttpStatus.OK);
		
	}
}
