package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Venta;
import com.mitocode.repo.IVentaRepo;
import com.mitocode.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	
	private IVentaRepo repo;
	
	
	@Override
	public Venta registrar(Venta t) {
		t.getDetalleventa().forEach(det->det.setVenta(t));
		return repo.save(t);
	}

	@Override
	public Venta modificar(Venta t) {
		
		return repo.save(t);
	}

	@Override
	public Venta leer(Integer id) {
		
		return repo.findOne(id);
	}

	@Override
	public List<Venta> listar() {
		
		return repo.findAll();
	}

	@Override
	public void elimiar(Integer id) {
		
		repo.delete(id);
	}

}
