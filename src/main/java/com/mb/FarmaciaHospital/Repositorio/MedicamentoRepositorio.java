package com.mb.FarmaciaHospital.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mb.FarmaciaHospital.Entidad.Medicamento;

/**
*
* @author Marcos Baró
*/

public interface MedicamentoRepositorio extends JpaRepository<Medicamento, Long> {

}
