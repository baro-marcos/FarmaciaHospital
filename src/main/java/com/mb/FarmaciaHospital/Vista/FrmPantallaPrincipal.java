package com.mb.FarmaciaHospital.Vista;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mb.FarmaciaHospital.Servicio.PacienteMedicamentoServicio;
import com.mb.FarmaciaHospital.Utilidad.Utilidades;

/**
*
* @author Marcos Baró
*/

public class FrmPantallaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// Variables estaticas para controlar las instancias abiertas de los JDialogs
    private static FrmCargaPaciente cargaPaciente = null;
    private JTextField txtDNI;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPantallaPrincipal frame = new FrmPantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPantallaPrincipal() {
		
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("Farmacia");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/farmacia_1616.png")));
		//setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1052, 639);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuPacientes = new JMenu("Pacientes");
		menuBar.add(menuPacientes);
		
		JMenuItem menuItemAltaPaciente = new JMenuItem("Carga Paciente");
		menuItemAltaPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Verificar si ya hay una instancia abierta del JDialog (Una Prueba)
                if (cargaPaciente == null || !cargaPaciente.isVisible()) {
                    // Crear una nueva instancia y mostrar el JDialog
                	cargaPaciente = new FrmCargaPaciente(FrmPantallaPrincipal.this); // lo hacemos modal
                    cargaPaciente.setLocationRelativeTo(null);
                    cargaPaciente.setVisible(true);
                }				
			}
		});
		menuPacientes.add(menuItemAltaPaciente);
		
		JMenuItem menuItemActualizarPaciente = new JMenuItem("Actualizar Paciente");
		menuItemActualizarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmActualizarPaciente pacienteActualizar = new FrmActualizarPaciente(FrmPantallaPrincipal.this);
				pacienteActualizar.setLocationRelativeTo(null);
				pacienteActualizar.setVisible(true);
				
			}
		});
		menuPacientes.add(menuItemActualizarPaciente);
		
		JMenuItem menuItemBorrarPaciente = new JMenuItem("Borrar Paciente");
		menuItemBorrarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmBorrarPaciente pacienteBorrar = new FrmBorrarPaciente(FrmPantallaPrincipal.this);
				pacienteBorrar.setLocationRelativeTo(null);
				pacienteBorrar.setVisible(true);
				
			}
		});
		menuPacientes.add(menuItemBorrarPaciente);
		
		JMenu menuMedicamentos = new JMenu("Medicamentos");
		menuBar.add(menuMedicamentos);
		
		JMenuItem menuItemAltaMedicamento = new JMenuItem("Carga Medicamento");
		menuItemAltaMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmCargaMedicamento medicamento = new FrmCargaMedicamento(FrmPantallaPrincipal.this);
				medicamento.setLocationRelativeTo(null);
				medicamento.setVisible(true);
				
			}
		});
		menuMedicamentos.add(menuItemAltaMedicamento);
		
		JMenuItem menuItemActualizarMedicamento = new JMenuItem("Actualizar Medicamento");
		menuItemActualizarMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmActualizarMedicamento medicamentoActualizar = new FrmActualizarMedicamento(FrmPantallaPrincipal.this);
				medicamentoActualizar.setLocationRelativeTo(null);
				medicamentoActualizar.setVisible(true);
			}
		});
		menuMedicamentos.add(menuItemActualizarMedicamento);
		
		JMenuItem menuItemBorrarMedicamento = new JMenuItem("Borrar Medicamento");
		menuItemBorrarMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmBorrarMedicamento medicamentoBorrar = new FrmBorrarMedicamento(FrmPantallaPrincipal.this);
				medicamentoBorrar.setLocationRelativeTo(null);
				medicamentoBorrar.setVisible(true);
				
			}
		});
		menuMedicamentos.add(menuItemBorrarMedicamento);
		
		JMenu menuPacienteMedicamento = new JMenu("Paciente-Medicamento");
		menuBar.add(menuPacienteMedicamento);
		
		JMenuItem menuItemCargaPacienteMedicamento = new JMenuItem("Carga Paciente - Medicamento");
		menuItemCargaPacienteMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmCargaPacienteMedicamento pacienteMedicamento = new FrmCargaPacienteMedicamento(FrmPantallaPrincipal.this);
				pacienteMedicamento.setLocationRelativeTo(null);
				pacienteMedicamento.setVisible(true);
				
			}
		});
		menuPacienteMedicamento.add(menuItemCargaPacienteMedicamento);
		
		JMenuItem menuItemBorrarPacienteMedicamento = new JMenuItem("Borrar Paciente - Medicamento");
		menuItemBorrarPacienteMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmBorrarPacienteMedicamento pacienteMedicamento = new FrmBorrarPacienteMedicamento(FrmPantallaPrincipal.this);
				pacienteMedicamento.setLocationRelativeTo(null);
				pacienteMedicamento.setVisible(true);
				
			}
		});
		menuPacienteMedicamento.add(menuItemBorrarPacienteMedicamento);
		
		JMenu menuInfo = new JMenu("Info");
		menuBar.add(menuInfo);
		
		JMenuItem menuItemAcercaDe = new JMenuItem("Acerca de...");
		menuItemAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmAcercaDe acercaDe = new FrmAcercaDe(FrmPantallaPrincipal.this);
				acercaDe.setLocationRelativeTo(null);
				acercaDe.setVisible(true);
				
			}
		});
		menuInfo.add(menuItemAcercaDe);
		getContentPane().setLayout(null);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBounds(849, 510, 140, 37);
		getContentPane().add(btnSalir);
		
		JLabel lblNroDocumento = new JLabel("Nro. Documento Paciente:");
		lblNroDocumento.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNroDocumento.setBounds(67, 80, 149, 24);
		getContentPane().add(lblNroDocumento);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(244, 83, 259, 19);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		JButton btnMedicamentosPersona = new JButton("Medicamentos por Paciente");
		btnMedicamentosPersona.setBounds(543, 82, 246, 21);
		getContentPane().add(btnMedicamentosPersona);
		
		JButton btnTotalMedicamentos = new JButton("Total Medicamentos");
		btnTotalMedicamentos.setBounds(67, 198, 305, 21);
		getContentPane().add(btnTotalMedicamentos);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(822, 82, 85, 21);
		getContentPane().add(btnLimpiar);
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				txtDNI.setText("");
				
			}
			
		});
		
		btnMedicamentosPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					if (txtDNI.getText().equals("")) { // .isEmpty()
						Utilidades.msg(btnMedicamentosPersona, "El DNI no puede estar vacío");
						txtDNI.requestFocus();
						return;
					}

					if (!Utilidades.isNumInt(txtDNI.getText())) {
		                Utilidades.msg(btnMedicamentosPersona, "DNI incorrecto");
		                txtDNI.setText("");
		                txtDNI.requestFocus();
		                return;
		            }
		            
		            if (Utilidades.isNumInt(txtDNI.getText()) && !(Integer.parseInt(txtDNI.getText()) > 0)) {
		                Utilidades.msg(btnMedicamentosPersona, "DNI incorrecto, debe ser mayor a 0");
		                txtDNI.setText("");
		                txtDNI.requestFocus();
		                return;                
		            }
										
					PacienteMedicamentoServicio pacienteMedicamentoServicio = new PacienteMedicamentoServicio();
					// Llamamos al metodo que consulta la base de datos
			        List<Object[]> medicamentos = pacienteMedicamentoServicio.obtenerMedicamentosPorPaciente(Integer.parseInt(txtDNI.getText()));
					
					if (medicamentos != null && !medicamentos.isEmpty()) {
						
						FrmReporteMedicamentos reportePacienteMedicamentos = new FrmReporteMedicamentos(FrmPantallaPrincipal.this, 
								medicamentos);
						reportePacienteMedicamentos.setLocationRelativeTo(null);
						reportePacienteMedicamentos.setVisible(true);
						
						txtDNI.setText("");
						
					} else {
						
						Utilidades.msg(btnMedicamentosPersona, "No se encontraron medicamentos para el DNI ingresado");
						
					}
					
				} catch (Exception ex) {
					
					Utilidades.msg(btnMedicamentosPersona, "Ocurrió un error al procesar el Paciente, intente nuevamente");
					txtDNI.setText("");
					return;
					
				}
				
			}
			
		});
		
		btnTotalMedicamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					PacienteMedicamentoServicio pacienteMedicamentoServicio = new PacienteMedicamentoServicio();
					// Llamamos al metodo que consulta la base de datos
			        List<Object[]> medicamentos = pacienteMedicamentoServicio.obtenerTotalMedicamentos();
					
					if (medicamentos != null && !medicamentos.isEmpty()) {
						
						FrmReporteMedicamentos reportePacienteMedicamentos = new FrmReporteMedicamentos(FrmPantallaPrincipal.this, 
								medicamentos);
						reportePacienteMedicamentos.setLocationRelativeTo(null);
						reportePacienteMedicamentos.setVisible(true);
						
					} else {
						
						Utilidades.msg(btnMedicamentosPersona, "No se encontraron medicamentos para mostrar");
						
					}
					
				} catch (Exception ex) {
					
					Utilidades.msg(btnMedicamentosPersona, "Ocurrió un error al procesar los Medicamentos, intente nuevamente");
					return;
					
				}
				
			}
		});
		
	}
}
