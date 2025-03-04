package com.mb.FarmaciaHospital.Entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
*
* @author Marcos Baró
*/

@Entity
@Table(name = "PACIENTE_MEDICAMENTO")
public class PacienteMedicamento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	@Column(name = "ID_PACIENTE_MEDICAMENTO")
    private Long idPacienteMedicamento;
	
//	@Column(name = "NRO_DOCUMENTO")
//    private Integer numeroDocumento;
//	
//	@Column(name = "ID_MEDICAMENTO")
//    private Long idMedicamento;
	
	@ManyToOne
    @JoinColumn(name = "NRO_DOCUMENTO") // Relacion con Paciente
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "ID_MEDICAMENTO") // Relacion con Medicamento
    private Medicamento medicamento;
	
	@Column(name = "CANTIDAD")
    private Integer cantidad;

	public PacienteMedicamento() {
		
	}

	public Long getIdPacienteMedicamento() {
		return idPacienteMedicamento;
	}

	public void setIdPacienteMedicamento(Long idPacienteMedicamento) {
		this.idPacienteMedicamento = idPacienteMedicamento;
	}

//	public Integer getNumeroDocumento() {
//		return numeroDocumento;
//	}
//
//	public void setNumeroDocumento(Integer numeroDocumento) {
//		this.numeroDocumento = numeroDocumento;
//	}
//
//	public Long getIdMedicamento() {
//		return idMedicamento;
//	}
//
//	public void setIdMedicamento(Long idMedicamento) {
//		this.idMedicamento = idMedicamento;
//	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

}
