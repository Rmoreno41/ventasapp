package com.mitocode.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "venta")
public class Venta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVenta;
	
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fecha;
	
	@ManyToOne
	@JoinColumn(name= "id_persona" , nullable= false , foreignKey = @ForeignKey(name="fk_venta_persona"))
	private Persona persona;
	
	@Column(name = "importe", nullable = false)
	private double importe;
	
	@OneToMany(mappedBy = "venta", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE},fetch = FetchType.LAZY, orphanRemoval = true)
	private List<DetalleVenta> detalleventa;

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public List<DetalleVenta> getDetalleventa() {
		return detalleventa;
	}

	public void setDetalleventa(List<DetalleVenta> detalleventa) {
		this.detalleventa = detalleventa;
	}
	
	

}
