package com.mb.FarmaciaHospital.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mb.FarmaciaHospital.Entidad.PacienteMedicamento;

/**
*
* @author Marcos Baró
*/

public interface PacienteMedicamentoRepositorio extends JpaRepository<PacienteMedicamento, Long> {

}
