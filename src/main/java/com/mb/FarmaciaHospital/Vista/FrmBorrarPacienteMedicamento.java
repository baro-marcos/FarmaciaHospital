package com.mb.FarmaciaHospital.Vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mb.FarmaciaHospital.Entidad.PacienteMedicamento;
import com.mb.FarmaciaHospital.Servicio.PacienteMedicamentoServicio;
import com.mb.FarmaciaHospital.Utilidad.Utilidades;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

/**
*
* @author Marcos Baró
*/

public class FrmBorrarPacienteMedicamento extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable jtablePacienteMedicamentos;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FrmBorrarPacienteMedicamento dialog = new FrmBorrarPacienteMedicamento();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FrmBorrarPacienteMedicamento(JFrame parent) {
		
		// El segundo parametro 'true' hace que el JDialog sea modal
        super(parent, "Borrar Paciente Medicamento", true);
        
        setLocationRelativeTo(parent); // Centrar el JDialog sobre el JFrame
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmBorrarPacienteMedicamento.class.getResource("/com/mb/FarmaciaHospital/Imagenes/pacientes.png")));
		
		setTitle("Borrar Paciente - Medicamento");
		setResizable(false);
		setBounds(100, 100, 829, 666);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar Paciente");
		btnBuscar.setBounds(284, 50, 247, 21);
		contentPanel.add(btnBuscar);
		
		// Crear la tabla
		jtablePacienteMedicamentos = new JTable();
		jtablePacienteMedicamentos.setBounds(30, 88, 757, 433);
		
		// Crear un JScrollPane para agregar el scroll a la tabla
	    JScrollPane scrollPane = new JScrollPane(jtablePacienteMedicamentos);
	    scrollPane.setBounds(30, 88, 757, 433);  // Establecer el mismo tamanio para que la tabla encaje
	    contentPanel.add(scrollPane);
		
		//contentPanel.add(jtablePacienteMedicamentos);
		
		JButton btnBorrar = new JButton("Eliminar");
		btnBorrar.setBounds(306, 562, 202, 21);
		contentPanel.add(btnBorrar);
		
		//Tabla
		final DefaultTableModel modelPacienteMedicamentos = new DefaultTableModel(
		        null, 
		        new String[]{"ID", "Paciente", "Medicamento", "Cantidad"}) {  // Definir los títulos de las columnas

		    // Celdas editables
		    boolean[] canEdit = new boolean[]{false, false, false, false};

		    @Override
		    public boolean isCellEditable(int rowIndex, int colIndex) {
		        return canEdit[colIndex];
		    }
		};
		
		jtablePacienteMedicamentos.setModel(modelPacienteMedicamentos);
		jtablePacienteMedicamentos.getTableHeader().setReorderingAllowed(false);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PacienteMedicamentoServicio pacienteMedicamentoServicio = new PacienteMedicamentoServicio();
				
				String dato = JOptionPane.showInputDialog("DNI:");
				
				if (dato != null && !dato.equals("") && Utilidades.isNumInt(dato) && Integer.parseInt(dato) > 0) {
					
					Integer datoEntero = Integer.valueOf(dato);
					
					List<PacienteMedicamento> pacienteMedicamentos = pacienteMedicamentoServicio.buscarPacienteMedicamentos(datoEntero);
					
					if (pacienteMedicamentos != null && !pacienteMedicamentos.isEmpty()) {
						
						// Cargamos el data table
						Utilidades.limpiarTabla(jtablePacienteMedicamentos, modelPacienteMedicamentos);
						pacienteMedicamentoServicio.llenarTabla(pacienteMedicamentos, 4, modelPacienteMedicamentos);
												
					} else {
						
						Utilidades.msg(btnBuscar, "No se econtró ningún Paciente Medicamento con ese DNI");
		                return;
						
					}
					
				} else {
	                
	                Utilidades.msg(btnBuscar, "Debe ingresar un DNI correcto que sea entero y mayor a 0, en la próxima búsqueda");
	                return;
	                
				}
				
			}
		});
		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PacienteMedicamentoServicio pacienteMedicamentoServicio = new PacienteMedicamentoServicio();
				
				// Obtener la fila seleccionada
		        int filaSeleccionada = jtablePacienteMedicamentos.getSelectedRow();
		        
		        if (filaSeleccionada != -1) {
		            // Obtenemos el ID del paciente medicamento
		            Long idPacienteMedicamento = (Long) jtablePacienteMedicamentos.getValueAt(filaSeleccionada, 0); //el id es la primer columna
		            
		            // Borramos
		            boolean borrado = pacienteMedicamentoServicio.borrarPacienteMedicamento(idPacienteMedicamento);
		            
		            if (borrado) {
						Utilidades.msg(btnBorrar, "Paciente - Medicamento borrado correctamente");
						dispose();
					} else {
						Utilidades.msg(btnBorrar, "Ocurrió un error al borrar el Paciente - Medicamento");
						dispose();
					}
		            
		        } else {
		            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla para eliminar.");
		        }
				
			}
		});
		
	}
}
