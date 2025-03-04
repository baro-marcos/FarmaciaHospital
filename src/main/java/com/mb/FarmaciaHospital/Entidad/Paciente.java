package com.mb.FarmaciaHospital.Entidad;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
*
* @author Marcos Baró
*/

@Entity
@Table(name = "PACIENTE")
public class Paciente {
	
	@Id
    @Column(name = "NRO_DOCUMENTO")
	private Integer numeroDocumento;
	
	@Column(name = "APELLIDO_NOMBRE")
	private String apellidoNombre;
	
	@Column(name = "DOMICILIO")
	private String domicilio;

	@Column(name = "LOCALIDAD")
	private String localidad;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "OS")
	private String os;
	
	@Column(name = "FECHA_NACIMIENTO")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name = "HC")
	private Integer hc;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PacienteMedicamento> pacienteMedicamentos;

	public Paciente() {
		
	}

	public Paciente(Integer numeroDocumento, String apellidoNombre, String domicilio, String localidad,
			String telefono, String os, Date fechaNacimiento, Integer hc) {
		super();
		this.numeroDocumento = numeroDocumento;
		this.apellidoNombre = apellidoNombre;
		this.domicilio = domicilio;
		this.localidad = localidad;
		this.telefono = telefono;
		this.os = os;
		this.fechaNacimiento = fechaNacimiento;
		this.hc = hc;
	}

	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getApellidoNombre() {
		return apellidoNombre;
	}

	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getHc() {
		return hc;
	}

	public void setHc(Integer hc) {
		this.hc = hc;
	}

	public List<PacienteMedicamento> getPacienteMedicamentos() {
		return pacienteMedicamentos;
	}

	public void setPacienteMedicamentos(List<PacienteMedicamento> pacienteMedicamentos) {
		this.pacienteMedicamentos = pacienteMedicamentos;
	}

}
