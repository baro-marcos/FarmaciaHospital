package com.mb.FarmaciaHospital.Vista;

import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mb.FarmaciaHospital.Entidad.Medicamento;
import com.mb.FarmaciaHospital.Entidad.Paciente;
import com.mb.FarmaciaHospital.Servicio.MedicamentoServicio;
import com.mb.FarmaciaHospital.Servicio.PacienteMedicamentoServicio;
import com.mb.FarmaciaHospital.Servicio.PacienteServicio;
import com.mb.FarmaciaHospital.Utilidad.Utilidades;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
*
* @author Marcos Baró
*/

public class FrmCargaPacienteMedicamento extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDNI;
	private JTextField txtPaciente;
	private JTextField txtIDMedicamento;
	private JTextField txtMedicamento;
	private JTextField txtCantidad;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FrmCargaPacienteMedicamento dialog = new FrmCargaPacienteMedicamento();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FrmCargaPacienteMedicamento(JFrame parent) {
		
		// El segundo parametro 'true' hace que el JDialog sea modal
        super(parent, "Carga Paciente Medicamento", true);
        
        setLocationRelativeTo(parent); // Centrar el JDialog sobre el JFrame
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/pacientes.png")));
		setTitle("Carga Paciente - Medicamento");
		setResizable(false);
		setBounds(100, 100, 820, 468);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDocu = new JLabel("Nro Documento");
		lblDocu.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDocu.setBounds(159, 36, 117, 25);
		contentPanel.add(lblDocu);
		
		txtDNI = new JTextField();
		txtDNI.setEditable(false);
		txtDNI.setColumns(10);
		txtDNI.setBounds(321, 39, 131, 19);
		contentPanel.add(txtDNI);
		
		JLabel lblApeNombre = new JLabel("Paciente");
		lblApeNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblApeNombre.setBounds(159, 71, 117, 25);
		contentPanel.add(lblApeNombre);
		
		txtPaciente = new JTextField();
		txtPaciente.setEditable(false);
		txtPaciente.setColumns(10);
		txtPaciente.setBounds(321, 74, 279, 19);
		contentPanel.add(txtPaciente);
		
		JButton btnBuscar = new JButton("Buscar Paciente");
		btnBuscar.setBounds(491, 36, 167, 21);
		contentPanel.add(btnBuscar);
		
		JComboBox comboBoxMedicamentos = new JComboBox();
		comboBoxMedicamentos.setMaximumRowCount(20);
		comboBoxMedicamentos.setBounds(76, 125, 644, 21);
		contentPanel.add(comboBoxMedicamentos);
		
		JLabel lblID = new JLabel("ID Medicamento");
		lblID.setBounds(76, 171, 96, 13);
		contentPanel.add(lblID);
		
		txtIDMedicamento = new JTextField();
		txtIDMedicamento.setEditable(false);
		txtIDMedicamento.setColumns(10);
		txtIDMedicamento.setBounds(192, 168, 143, 19);
		contentPanel.add(txtIDMedicamento);
		
		JLabel lblMedicamento = new JLabel("Medicamento");
		lblMedicamento.setBounds(76, 209, 143, 13);
		contentPanel.add(lblMedicamento);
		
		txtMedicamento = new JTextField();
		txtMedicamento.setEditable(false);
		txtMedicamento.setColumns(10);
		txtMedicamento.setBounds(76, 235, 644, 19);
		contentPanel.add(txtMedicamento);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(232, 374, 131, 21);
		contentPanel.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(446, 374, 131, 21);
		contentPanel.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxMedicamentos.setSelectedIndex(0);
				
				txtDNI.setText("");
				txtPaciente.setText("");
				
				txtIDMedicamento.setText("");
				txtMedicamento.setText("");
				
				txtCantidad.setText("");
				
				
				
			}
		});
		
		// Cargo el comboBox con los Medicamentos
		MedicamentoServicio medicamentoServicio = new MedicamentoServicio();
				
		List<Medicamento> medicamentos = medicamentoServicio.buscarMedicamentos();
				
		DefaultComboBoxModel<Medicamento> model = new DefaultComboBoxModel<>();
		model.removeAllElements();
		for (Medicamento medicamento : medicamentos) {
			model.addElement(medicamento);
		}
		comboBoxMedicamentos.setModel(model);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(76, 305, 69, 13);
		contentPanel.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(192, 302, 143, 19);
		contentPanel.add(txtCantidad);
		txtCantidad.setColumns(10);
		        
		comboBoxMedicamentos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Medicamento selectedMedicamento = (Medicamento) comboBoxMedicamentos.getSelectedItem();
		        if (selectedMedicamento != null) {
		        	// Actualizar los JTextField con el id y el nombre del medicamento seleccionado
		            txtIDMedicamento.setText(String.valueOf(selectedMedicamento.getIdMedicamento()));
		            txtMedicamento.setText(selectedMedicamento.getNombreMedicamento());
		         }
						
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				PacienteServicio pacienteServicio = new PacienteServicio();
				
				String dato = JOptionPane.showInputDialog("DNI:");
				
				if (dato != null && !dato.equals("") && Utilidades.isNumInt(dato) && Integer.parseInt(dato) > 0) {
					
					Integer datoEntero = Integer.valueOf(dato);
					
					Paciente paciente = pacienteServicio.buscarPaciente(datoEntero);
					
					if (paciente != null) {
						
						// Cargamos los campos
						txtDNI.setText(String.valueOf(paciente.getNumeroDocumento()));
						txtPaciente.setText(paciente.getApellidoNombre());
												
					} else {
						
						Utilidades.msg(btnBuscar, "No se econtró ningún Paciente con ese DNI");
		                return;
						
					}
					
				} else {
	                
	                Utilidades.msg(btnBuscar, "Debe ingresar un DNI correcto que sea entero y mayor a 0, en la próxima búsqueda");
	                return;
	                
				}
				
			}
		});
		
		btnAgregar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				try {

					if (txtDNI.getText().equals("") || txtPaciente.getText().equals("")) {
						Utilidades.msg(btnAgregar, "El DNI y/o Paciente no pueden estar vacíos, buscar nuevamente");
						return;
					}

					if (txtIDMedicamento.getText().equals("") || txtMedicamento.getText().equals("")) {
						Utilidades.msg(btnAgregar,
								"El ID y/o el nombre del medicamento no pueden estar vacíos, seleccionar nuevamente");
						return;
					}

					if (txtCantidad.getText().equals("")) {
						Utilidades.msg(btnAgregar, "La cantidad no puede estar vacía");
						txtCantidad.requestFocus();
						return;
					}

					if (Utilidades.isNumInt(txtCantidad.getText()) && !(Integer.parseInt(txtCantidad.getText()) > 0)) {
						Utilidades.msg(btnAgregar, "Cantidad incorrecta, debe ser mayor a 0");
						txtCantidad.setText("");
						txtCantidad.requestFocus();
						return;
					}

					PacienteMedicamentoServicio pacienteMedicamento = new PacienteMedicamentoServicio();
					
					pacienteMedicamento.insertarPacienteMedicamento(Long.parseLong(txtIDMedicamento.getText()), 
							Integer.parseInt(txtDNI.getText()), Integer.parseInt(txtCantidad.getText()));
					
					Utilidades.msg(btnAgregar, "Paciente - Medicamento ingresado correctamente");
					
					comboBoxMedicamentos.setSelectedIndex(0);
					
					txtDNI.setText("");
					txtPaciente.setText("");
					
					txtIDMedicamento.setText("");
					txtMedicamento.setText("");
					
					txtCantidad.setText("");
					
					//dispose();

				} catch (NumberFormatException e1) {

					Utilidades.msg(btnAgregar, "Ocurrió un error al ingresar el Paciente - Medicamento, intente nuevamente");
					dispose();

				} catch (Exception e1) {

					Utilidades.msg(btnAgregar, "Ocurrió un error al ingresar el Paciente - Medicamento, intente nuevamente");
					dispose();

				}

			}
		});
		
	}
}
