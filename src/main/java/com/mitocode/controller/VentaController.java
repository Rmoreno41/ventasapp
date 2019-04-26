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

import com.mitocode.model.Venta;
import com.mitocode.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private IVentaService service;
	
	
	
	
	@GetMapping
	public ResponseEntity <List<Venta>> mostrar() {
		 List<Venta> pacientes = service.listar();
		return new ResponseEntity<List<Venta>>(pacientes,HttpStatus.OK);
	}
	
	
	@GetMapping//(produces = "application/xml")
	@RequestMapping("/{id}")
	public ResponseEntity<Venta> listarPorId(@PathVariable("id") Integer idVenta) {
		Venta pac= service.leer(idVenta);
		return new ResponseEntity<Venta>(pac,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar (@Valid @RequestBody Venta venta) {
		Venta pac= service.registrar(venta);
		// crear url para enviar por el response y mostrar en el header
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar (@Valid @RequestBody Venta per) {
		Venta pac= service.modificar(per);
		return new ResponseEntity<Object>(pac,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  eliminar (@PathVariable("id")Integer IdVenta) {
		Venta pac= service.leer(IdVenta);
		return new ResponseEntity<Object>(pac,HttpStatus.OK);
		
	}
}
