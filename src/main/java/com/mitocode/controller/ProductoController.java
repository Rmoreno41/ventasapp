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

import com.mitocode.model.Producto;
import com.mitocode.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;
	
	
	
	
	@GetMapping
	public ResponseEntity <List<Producto>> mostrar() {
		 List<Producto> pacientes = service.listar();
		return new ResponseEntity<List<Producto>>(pacientes,HttpStatus.OK);
	}
	
	
	@GetMapping//(produces = "application/xml")
	@RequestMapping("/{id}")
	public ResponseEntity<Producto> listarPorId(@PathVariable("id") Integer idProducto) {
		Producto pac= service.leer(idProducto);
		return new ResponseEntity<Producto>(pac,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar (@Valid @RequestBody Producto paciente) {
		Producto pac= service.registrar(paciente);
		// crear url para enviar por el response y mostrar en el header
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar (@Valid @RequestBody Producto per) {
		Producto pac= service.modificar(per);
		return new ResponseEntity<Object>(pac,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  eliminar (@PathVariable("id")Integer IdProducto) {
		Producto pac= service.leer(IdProducto);
		return new ResponseEntity<Object>(pac,HttpStatus.OK);
		
	}
}
