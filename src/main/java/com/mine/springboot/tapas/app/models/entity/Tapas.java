package com.mine.springboot.tapas.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tapa")
public class Tapas implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private float precio;
	private int valoracion;
	private boolean preciovariable;

	@ElementCollection
	private List<String> hastag;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat
	private Date createAt;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "comida_id")
	@JsonBackReference
	private Comida comida;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "bebida_id")
	@JsonBackReference
	private Bebida bebida;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bar_id")
	@JsonBackReference
	private Bar bar;

	@OneToMany(mappedBy = "tapa", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonBackReference
	private List<Vote> vote;

	// @Column(name = "number_of_votes")
	// private Long numberOfVotes;

	public Tapas() {
		this.hastag = new ArrayList<String>();
		this.vote = new ArrayList<Vote>();
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		createAt = new Date();
	}

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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public boolean isPreciovariable() {
		return preciovariable;
	}

	public void setPreciovariable(boolean preciovariable) {
		this.preciovariable = preciovariable;
	}

	public List<String> getHastag() {
		return hastag;
	}

	public void setHastag(List<String> hastag) {
		this.hastag = hastag;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Comida getComida() {
		return comida;
	}

	public void setComida(Comida comida) {
		this.comida = comida;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	public List<Vote> getVote() {
		return vote;
	}

	public void setVote(List<Vote> vote) {
		this.vote = vote;
	}

	private static final long serialVersionUID = 1L;

}
