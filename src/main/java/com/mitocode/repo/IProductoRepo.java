package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Integer>{

}
