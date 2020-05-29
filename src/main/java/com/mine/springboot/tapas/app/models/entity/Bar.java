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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "bar")
public class Bar implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String ubicacion;
	private String descripcion;
	private String nif;

	@ElementCollection
	private List<String> hastag;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat
	private Date createAt;

	@OneToMany(mappedBy = "bar", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tapas> tapas;

	@OneToMany(mappedBy = "bar", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Raciones> raciones;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private Usuario users;

	private String foto;
	private String fotonif;

	public Bar() {
		this.tapas = new ArrayList<Tapas>();
		this.raciones = new ArrayList<Raciones>();
		this.hastag = new ArrayList<String>();
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public List<Tapas> getTapas() {
		return tapas;
	}

	public void setTapas(List<Tapas> tapas) {
		this.tapas = tapas;
	}

	public void addTapas(Tapas tapa) {
		this.tapas.add(tapa);
	}

	public List<Raciones> getRaciones() {
		return raciones;
	}

	public void setRaciones(List<Raciones> raciones) {
		this.raciones = raciones;
	}

	public void addRaciones(Raciones raciones) {
		this.raciones.add(raciones);
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFotonif() {
		return fotonif;
	}

	public void setFotonif(String fotonif) {
		this.fotonif = fotonif;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Usuario getUsers() {
		return users;
	}

	public void setUsers(Usuario users) {
		this.users = users;
	}

	private static final long serialVersionUID = 1L;

}
