package com.mb.FarmaciaHospital.Servicio;

import java.util.List;

import com.mb.FarmaciaHospital.Entidad.Medicamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

/**
*
* @author Marcos Baró
*/

public class MedicamentoServicio {
	
	public void insertarMedicamento(String nombreMedicamento) throws Exception {
		
		// Insert usando JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
        EntityManager em = emf.createEntityManager();

        try {
        	
            em.getTransaction().begin();
            
            Medicamento medicamento = new Medicamento();
            medicamento.setNombreMedicamento(nombreMedicamento);
                        
            em.persist(medicamento);
            
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
	
	public List<Medicamento> buscarMedicamentos() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
		EntityManager em = emf.createEntityManager();
		
		List<Medicamento> medicamentos = null;
		
		try {
			
			String jpqlSelect = "SELECT m FROM Medicamento m";
	        Query query = em.createQuery(jpqlSelect);
	        medicamentos = query.getResultList();
	   	
		} catch (Exception e) {
			
			medicamentos = List.of();  // Devuelve una lista vacia		
		
		} finally {
        	
            em.close();
            emf.close();
            
        }
		
		return medicamentos;
		
	}
	
	public Medicamento actualizarMedicamento (Long idMedicamento, String nombreMedicamento) {
		
		Medicamento medicamento = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
		EntityManager em = emf.createEntityManager();
		
		try {
		
			medicamento = em.find(Medicamento.class, idMedicamento);
			
			if (medicamento != null) {
				
				em.getTransaction().begin();
				
				// Cargamos los datos
				medicamento.setNombreMedicamento(nombreMedicamento);
												
				em.getTransaction().commit();
				
			}
			
		} catch (Exception e) {
			
			if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
			
			return medicamento;
			
		} finally {
        	
            em.close();
            emf.close();
            
        }
		
		return medicamento;
		
	}
	
	public boolean borrarMedicamento(Long idMedicamento) {
		
		boolean borrado = false;
		
		Medicamento medicamento = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
		EntityManager em = emf.createEntityManager();
		
		try {
		
			medicamento = em.find(Medicamento.class, idMedicamento);
			
			if (medicamento != null) {
				
				em.getTransaction().begin();
				
				// Borramos el Medicamento
				em.remove(medicamento);
								
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
