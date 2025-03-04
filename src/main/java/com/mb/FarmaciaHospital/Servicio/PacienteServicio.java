package com.mb.FarmaciaHospital.Servicio;

import java.util.Date;

import com.mb.FarmaciaHospital.Entidad.Paciente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
*
* @author Marcos Baró
*/

public class PacienteServicio {
	
	public void insertarPaciente(Integer numeroDocumento, String apellidoNombre, String domicilio, String localidad, String tel,
			Date fechaNac, String os, Integer hc) throws Exception {
		
		// Insert usando JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
        EntityManager em = emf.createEntityManager();

        try {
        	
            em.getTransaction().begin();
            
            Paciente paciente = new Paciente();
            paciente.setNumeroDocumento(numeroDocumento);
            paciente.setApellidoNombre(apellidoNombre);
            paciente.setDomicilio(domicilio);
            paciente.setLocalidad(localidad);
            paciente.setTelefono(tel);
            paciente.setFechaNacimiento(fechaNac);
            paciente.setOs(os);
            paciente.setHc(hc);
            
            em.persist(paciente);
            
            em.getTransaction().commit();
            
        } catch (Exception e) {
        	
        	if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        	
            //e.printStackTrace();
            throw e;
            
        } finally {
        	
            em.close();
            emf.close();
            
        }
		
	}
	
	public Paciente buscarPaciente(Integer numeroDocumento) {
		
		Paciente paciente = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
		EntityManager em = emf.createEntityManager();
		
		try {
			
			paciente = em.find(Paciente.class, numeroDocumento);
			
		} catch (Exception e) {
			
			return paciente;
			
		} finally {
        	
            em.close();
            emf.close();
            
        }
		
		return paciente;
		
	}
	
	public Paciente actualizarPaciente (Integer numeroDocumento, String apellidoNombre, String domicilio, String localidad, String tel,
			Date fechaNac, String os, Integer hc) {
		
		Paciente paciente = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
		EntityManager em = emf.createEntityManager();
		
		try {
		
			paciente = em.find(Paciente.class, numeroDocumento);
			
			if (paciente != null) {
				
				em.getTransaction().begin();
				
				// Cargamos los datos
				paciente.setApellidoNombre(apellidoNombre);
				paciente.setDomicilio(domicilio);
				paciente.setLocalidad(localidad);
				paciente.setTelefono(tel);
				paciente.setFechaNacimiento(fechaNac);
				paciente.setOs(os);
				paciente.setHc(hc);
								
				em.getTransaction().commit();
				
			}
			
		} catch (Exception e) {
			
			if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
			
			return paciente;
			
		} finally {
        	
            em.close();
            emf.close();
            
        }
		
		return paciente;
		
	}
	
	public boolean borrarPaciente(Integer numeroDocumento) {
		
		boolean borrado = false;
		
		Paciente paciente = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
		EntityManager em = emf.createEntityManager();
		
		try {
		
			paciente = em.find(Paciente.class, numeroDocumento);
			
			if (paciente != null) {
				
				em.getTransaction().begin();
				
				// Borramos al paciente
				em.remove(paciente);
								
				em.getTransaction().commit();
				
				borrado = true;
				
			}
			
		} catch (Exception e) {
			
			if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
			
			return borrado;
			
		} finally {
        	
            em.close();
            emf.close();
            
        }
		
		return borrado;
		
	}

}
