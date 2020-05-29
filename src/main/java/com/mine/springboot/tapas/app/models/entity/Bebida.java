package com.mine.springboot.tapas.app.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "bebida")
public class Bebida implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoriabebida_id")
	@JsonBackReference
	private CategoriaBebida categoriabebida;

	@OneToOne(optional = false, mappedBy = "bebida", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Tapas tapas;
	
	@OneToOne(optional = false, mappedBy = "bebida", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Raciones raciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CategoriaBebida getCategoriabebida() {
		return categoriabebida;
	}

	public void setCategoriabebida(CategoriaBebida categoriabebida) {
		this.categoriabebida = categoriabebida;
	}

	public Tapas getTapas() {
		return tapas;
	}

	public void setTapas(Tapas tapas) {
		this.tapas = tapas;
	}

	public Raciones getRaciones() {
		return raciones;
	}

	public void setRaciones(Raciones raciones) {
		this.raciones = raciones;
	}



	private static final long serialVersionUID = 1L;

}
