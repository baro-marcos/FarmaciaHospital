package com.mb.FarmaciaHospital.Servicio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.mb.FarmaciaHospital.Entidad.Medicamento;
import com.mb.FarmaciaHospital.Entidad.Paciente;
import com.mb.FarmaciaHospital.Entidad.PacienteMedicamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

/**
*
* @author Marcos Baró
*/

public class PacienteMedicamentoServicio {
	
	public void insertarPacienteMedicamento(Long idMedicamento, Integer numeroDocumento, Integer cantidad) throws Exception {
		
		// Insert usando JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
        EntityManager em = emf.createEntityManager();

        try {
        	
            em.getTransaction().begin();
            
            // Creamos una instancia de Paciente y Medicamento desde los identificadores
            Paciente paciente = em.find(Paciente.class, numeroDocumento);
            Medicamento medicamento = em.find(Medicamento.class, idMedicamento);
            
            if (paciente == null) {
                throw new Exception("Paciente no encontrado con el numero de documento: " + numeroDocumento);
            }
            if (medicamento == null) {
                throw new Exception("Medicamento no encontrado con el id: " + idMedicamento);
            }

            // Crear la instancia de PacienteMedicamento
            PacienteMedicamento pacienteMedicamento = new PacienteMedicamento();
            pacienteMedicamento.setPaciente(paciente);  // Asigna el paciente encontrado
            pacienteMedicamento.setMedicamento(medicamento);  // Asigna el medicamento encontrado
            pacienteMedicamento.setCantidad(cantidad);

            // Persistir el objeto PacienteMedicamento
            em.persist(pacienteMedicamento);
            
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

	public List<PacienteMedicamento> buscarPacienteMedicamentos(Integer numeroDocumento) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
		EntityManager em = emf.createEntityManager();

		List<PacienteMedicamento> pacienteMedicamentos = null;

		try {

			String jpqlSelect = "SELECT pm FROM PacienteMedicamento pm WHERE pm.paciente.numeroDocumento = :numeroDocumento";
			
			Query query = em.createQuery(jpqlSelect);
			
			// Establecemos el valor del parametro en la consulta
	        query.setParameter("numeroDocumento", numeroDocumento);
			
			pacienteMedicamentos = query.getResultList();

		} catch (Exception e) {

			pacienteMedicamentos = List.of(); // Devuelve una lista vacia

		} finally {

			em.close();
			emf.close();

		}

		return pacienteMedicamentos;

	}
	
	public void llenarTabla(List<PacienteMedicamento> listaPacienteMedicamentos, int cantidadColumnas, DefaultTableModel model) {
		
		for (PacienteMedicamento pacienteMedicamento: listaPacienteMedicamentos) {
            
			Object[] fila = new Object[cantidadColumnas];
            
            fila[0] = pacienteMedicamento.getIdPacienteMedicamento();
            fila[1] = pacienteMedicamento.getPaciente().getApellidoNombre();
            fila[2] = pacienteMedicamento.getMedicamento().getNombreMedicamento();
            fila[3] = pacienteMedicamento.getCantidad();
            
            // Se añade al modelo la fila completa.
            model.addRow(fila);
            
        }
		
	}
	
	public boolean borrarPacienteMedicamento(Long idPacienteMedicamento) {
		
		boolean borrado = false;
		
		PacienteMedicamento pacienteMedicamento = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
		EntityManager em = emf.createEntityManager();
		
		try {
		
			pacienteMedicamento = em.find(PacienteMedicamento.class, idPacienteMedicamento);
			
			if (pacienteMedicamento != null) {
				
				em.getTransaction().begin();
				
				// Borramos el paciente medicamento
				em.remove(pacienteMedicamento);
								
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
	
	public List<Object[]> obtenerMedicamentosPorPaciente(Integer numeroDocumento) {

		// Creando la conexion a la base de datos
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
		EntityManager em = emf.createEntityManager();

		List<Object[]> medicamentos = new ArrayList<>();

		try {

			// Agrupa los medicamentos por nombre y suma las cantidades
			String jpqlSelect = "SELECT m.medicamento.nombreMedicamento, SUM(m.cantidad) "
					+ " FROM PacienteMedicamento m "
					+ " WHERE m.paciente.numeroDocumento = :numeroDocumento "
					+ " GROUP BY m.medicamento.nombreMedicamento "
					+ " ORDER BY m.medicamento.nombreMedicamento";
			
			Query query = em.createQuery(jpqlSelect);
			
			// Establecemos el valor del parametro en la consulta
	        query.setParameter("numeroDocumento", numeroDocumento);
			
	        // Ejecutamos la consulta y obtenemos los resultados
            List<Object[]> resultado = query.getResultList();
            
            // Asignamos los resultados a la lista de medicamentos
            for (Object[] row : resultado) {
                medicamentos.add(row); // Aqui row[0] es nombre y row[1] es la suma de cantidades
            }

		} catch (Exception e) {

			medicamentos = List.of(); // Devuelve una lista vacia

		} finally {

			em.close();
			emf.close();

		}

		return medicamentos;

	}
	
	public void llenarTablaReporte(List<Object[]> listaMedicamentos, DefaultTableModel model) {
		
		for (Object[] row : listaMedicamentos) {
			model.addRow(row);
		
		}
		
	}
	
	public List<Object[]> obtenerTotalMedicamentos() {

		// Creando la conexion a la base de datos
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
		EntityManager em = emf.createEntityManager();

		List<Object[]> medicamentos = new ArrayList<>();

		try {

			// Agrupa los medicamentos por nombre y suma las cantidades
			String jpqlSelect = "SELECT m.medicamento.nombreMedicamento, SUM(m.cantidad) "
					+ " FROM PacienteMedicamento m "
					+ " GROUP BY m.medicamento.nombreMedicamento "
					+ " ORDER BY m.medicamento.nombreMedicamento";
			
			Query query = em.createQuery(jpqlSelect);
						
	        // Ejecutamos la consulta y obtenemos los resultados
            List<Object[]> resultado = query.getResultList();
            
            // Asignamos los resultados a la lista de medicamentos
            for (Object[] row : resultado) {
                medicamentos.add(row); // Aqui row[0] es nombre y row[1] es la suma de cantidades
            }

		} catch (Exception e) {

			medicamentos = List.of(); // Devuelve una lista vacia

		} finally {

			em.close();
			emf.close();

		}

		return medicamentos;

	}

}
