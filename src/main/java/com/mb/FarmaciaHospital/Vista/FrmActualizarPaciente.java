package com.mb.FarmaciaHospital.Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mb.FarmaciaHospital.Entidad.Paciente;
import com.mb.FarmaciaHospital.Servicio.PacienteServicio;
import com.mb.FarmaciaHospital.Utilidad.Utilidades;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

/**
*
* @author Marcos Baró
*/

public class FrmActualizarPaciente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDocumento;
	private JTextField txtPaciente;
	private JTextField txtDomicilio;
	private JTextField txtLocalidad;
	private JTextField txtTelefono;
	private JTextField txtHC;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FrmActualizarPaciente dialog = new FrmActualizarPaciente();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FrmActualizarPaciente(JFrame parent) {
		
		// El segundo parametro 'true' hace que el JDialog sea modal
        super(parent, "Actualizar Paciente", true);
        
        setLocationRelativeTo(parent); // Centrar el JDialog sobre el JFrame
		
		setResizable(false);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCargaPaciente.class.getResource("/com/mb/FarmaciaHospital/Imagenes/pacientes.png")));
		setTitle("Actualizar Paciente");
		setBounds(100, 100, 567, 473);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDocu = new JLabel("Nro Documento");
		lblDocu.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDocu.setBounds(31, 40, 117, 25);
		contentPanel.add(lblDocu);
		
		JLabel lblApeNombre = new JLabel("Paciente");
		lblApeNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblApeNombre.setBounds(31, 75, 117, 25);
		contentPanel.add(lblApeNombre);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDomicilio.setBounds(31, 110, 117, 25);
		contentPanel.add(lblDomicilio);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblLocalidad.setBounds(31, 145, 117, 25);
		contentPanel.add(lblLocalidad);
		
		JLabel lblTel = new JLabel("Teléfono");
		lblTel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTel.setBounds(31, 181, 117, 25);
		contentPanel.add(lblTel);
		
		JLabel lblFechaNac = new JLabel("Fecha Nacimiento");
		lblFechaNac.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaNac.setBounds(31, 216, 117, 25);
		contentPanel.add(lblFechaNac);
		
		JLabel lblOS = new JLabel("O. S.");
		lblOS.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOS.setBounds(31, 251, 117, 25);
		contentPanel.add(lblOS);
		
		JLabel lblHC = new JLabel("H. C.");
		lblHC.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblHC.setBounds(31, 286, 117, 25);
		contentPanel.add(lblHC);
		
		txtDocumento = new JTextField();
		txtDocumento.setEnabled(false);
		txtDocumento.setBounds(193, 43, 131, 19);
		contentPanel.add(txtDocumento);
		txtDocumento.setColumns(10);
		
		txtPaciente = new JTextField();
		txtPaciente.setColumns(10);
		txtPaciente.setBounds(193, 78, 279, 19);
		contentPanel.add(txtPaciente);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setColumns(10);
		txtDomicilio.setBounds(193, 113, 279, 19);
		contentPanel.add(txtDomicilio);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(193, 148, 279, 19);
		contentPanel.add(txtLocalidad);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(193, 184, 279, 19);
		contentPanel.add(txtTelefono);
				
		txtHC = new JTextField();
		txtHC.setColumns(10);
		txtHC.setBounds(193, 289, 131, 19);
		contentPanel.add(txtHC);
		
		JComboBox comboBoxOS = new JComboBox();
		comboBoxOS.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		comboBoxOS.setBounds(193, 253, 65, 21);
		contentPanel.add(comboBoxOS);
		
		//JCalendar calendarFechaNac = new JCalendar();
		//calendarFechaNac.setBounds(193, 219, 250, 250);//setBounds(193, 219, 300, 19);
		// Hacer visible el calendario completo
		//calendarFechaNac.setWeekOfYearVisible(false);  // Muestra la semana del anio, si es necesario
		//calendarFechaNac.setTodayButtonVisible(false);  // Muestra un botón para seleccionar hoy
		//calendarFechaNac.setTodayButtonText("Hoy");   // Puedes cambiar el texto del botón de hoy
		JDateChooser calendarFechaNac = new JDateChooser();
		calendarFechaNac.setDateFormatString("yyyy-MM-dd");  // Establecer el formato de fecha
		calendarFechaNac.setBounds(193, 219, 150, 19);
		//calendarFechaNac.setPreferredSize(new Dimension(150, 30)); // Tamaño del DateChooser
		calendarFechaNac.setDate(new Date());
		contentPanel.add(calendarFechaNac);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(127, 343, 131, 21);
		contentPanel.add(btnActualizar);
				
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(341, 343, 131, 21);
		contentPanel.add(btnCancelar);
		
		JButton btnBuscar = new JButton("Buscar Paciente");
		btnBuscar.setBounds(363, 40, 139, 21);
		contentPanel.add(btnBuscar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtDocumento.setText("");
				txtPaciente.setText("");
				txtDomicilio.setText("");
				txtLocalidad.setText("");
				txtTelefono.setText("");
				calendarFechaNac.setDate(new Date());
				comboBoxOS.setSelectedIndex(0);
				txtHC.setText("");
				
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
						txtDocumento.setText(String.valueOf(paciente.getNumeroDocumento()));
						txtPaciente.setText(paciente.getApellidoNombre());
						txtDomicilio.setText(paciente.getDomicilio());
						txtLocalidad.setText(paciente.getLocalidad());
						txtTelefono.setText(paciente.getTelefono());
						calendarFechaNac.setDate(paciente.getFechaNacimiento());
						comboBoxOS.setSelectedItem(paciente.getOs());
						txtHC.setText(String.valueOf(paciente.getHc()));
						
						
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
		
		btnActualizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
				
				if (txtPaciente.getText().equals("")) {
					Utilidades.msg(btnActualizar, "El paciente no puede estar vacío");
					txtPaciente.requestFocus();
					return;
				}

				if (txtDomicilio.getText().equals("")) {
					Utilidades.msg(btnActualizar, "El domicilio no puede estar vacío");
					txtDomicilio.requestFocus();
					return;
				}

				if (txtLocalidad.getText().equals("")) {
					Utilidades.msg(btnActualizar, "La localidad no puede estar vacía");
					txtLocalidad.requestFocus();
					return;
				}

				if (txtTelefono.getText().equals("")) {
					Utilidades.msg(btnActualizar, "El teléfono no puede estar vacío");
					txtTelefono.requestFocus();
					return;
				}

				if (txtHC.getText().equals("")) {
					Utilidades.msg(btnActualizar, "La HC no puede estar vacía");
					txtHC.requestFocus();
					return;
				}

				if (!Utilidades.isNumInt(txtHC.getText())) {
					Utilidades.msg(btnActualizar, "HC incorrecta, debe ser un número entero mayor a 0");
					txtHC.setText("");
					txtHC.requestFocus();
					return;
				}
				
				if (Utilidades.isNumInt(txtHC.getText()) && !(Integer.parseInt(txtHC.getText()) > 0)) {
	                Utilidades.msg(btnActualizar, "HC incorrecta, debe ser mayor a 0");
	                txtHC.setText("");
	                txtHC.requestFocus();
	                return;                
	            }
				
				PacienteServicio pacienteServicio = new PacienteServicio();
				
				Paciente paciente = pacienteServicio.actualizarPaciente(Integer.valueOf(txtDocumento.getText()), txtPaciente.getText(),
						txtDomicilio.getText(), txtLocalidad.getText(), txtTelefono.getText(),
						calendarFechaNac.getDate(), comboBoxOS.getSelectedItem().toString(),
						Integer.valueOf(txtHC.getText()));
				
				if (paciente != null) {
					
					Utilidades.msg(btnActualizar, "Paciente actualizado correctamente");
					dispose();
					
				} else {
					
					Utilidades.msg(btnActualizar, "Ocurrió un error al Actualizar el Paciente, intente nuevamente");
					dispose();
					
				}
				
				} catch (NumberFormatException e1) {
					
					Utilidades.msg(btnActualizar, "Ocurrió un error al Actualizar el Paciente, intente nuevamente");
					dispose();
					
				}
			
			}
			
		});
		
	}
}
