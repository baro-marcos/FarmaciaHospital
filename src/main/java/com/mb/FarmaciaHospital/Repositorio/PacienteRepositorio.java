package com.mb.FarmaciaHospital.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mb.FarmaciaHospital.Entidad.Paciente;

/**
*
* @author Marcos Baró
*/

public interface PacienteRepositorio extends JpaRepository<Paciente, Integer> {

}
