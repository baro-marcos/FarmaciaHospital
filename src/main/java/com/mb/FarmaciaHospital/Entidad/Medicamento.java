package com.mb.FarmaciaHospital.Entidad;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
*
* @author Marcos Baró
*/

@Entity
@Table(name = "MEDICAMENTO")
public class Medicamento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	@Column(name = "ID_MEDICAMENTO")
    private Long idMedicamento;
	
	@Column(name = "NOMBRE_MEDICAMENTO")
	private String nombreMedicamento;
	
	@OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PacienteMedicamento> pacienteMedicamentos;

	public Medicamento() {
		
	}

	public Medicamento(Long idMedicamento, String nombreMedicamento) {
		super();
		this.idMedicamento = idMedicamento;
		this.nombreMedicamento = nombreMedicamento;
	}

	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getNombreMedicamento() {
		return nombreMedicamento;
	}

	public void setNombreMedicamento(String nombreMedicamento) {
		this.nombreMedicamento = nombreMedicamento;
	}

	public List<PacienteMedicamento> getPacienteMedicamentos() {
		return pacienteMedicamentos;
	}

	public void setPacienteMedicamentos(List<PacienteMedicamento> pacienteMedicamentos) {
		this.pacienteMedicamentos = pacienteMedicamentos;
	}
	
	@Override
    public String toString() {
        return nombreMedicamento;
    }

}
