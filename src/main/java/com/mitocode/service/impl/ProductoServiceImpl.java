package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Producto;
import com.mitocode.repo.IProductoRepo;
import com.mitocode.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	
	private IProductoRepo repo;
	
	
	@Override
	public Producto registrar(Producto t) {
		return repo.save(t);
	}

	@Override
	public Producto modificar(Producto t) {
		
		return repo.save(t);
	}

	@Override
	public Producto leer(Integer id) {
		
		return repo.findOne(id);
	}

	@Override
	public List<Producto> listar() {
		
		return repo.findAll();
	}

	@Override
	public void elimiar(Integer id) {
		
		repo.delete(id);
	}

}
