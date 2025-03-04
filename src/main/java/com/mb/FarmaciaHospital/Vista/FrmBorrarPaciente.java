package com.mb.FarmaciaHospital.Vista;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mb.FarmaciaHospital.Servicio.PacienteServicio;
import com.mb.FarmaciaHospital.Utilidad.Utilidades;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
*
* @author Marcos Baró
*/

public class FrmBorrarPaciente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDocumento;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FrmBorrarPaciente dialog = new FrmBorrarPaciente();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FrmBorrarPaciente(JFrame parent) {
		
		// El segundo parametro 'true' hace que el JDialog sea modal
        super(parent, "Borrar Paciente", true);
        
        setLocationRelativeTo(parent); // Centrar el JDialog sobre el JFrame
				
		setResizable(false);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCargaPaciente.class.getResource("/com/mb/FarmaciaHospital/Imagenes/pacientes.png")));
		setTitle("Borrar Paciente");
		
		setBounds(100, 100, 400, 251);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDocu = new JLabel("Nro Documento");
		lblDocu.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDocu.setBounds(42, 55, 117, 25);
		contentPanel.add(lblDocu);
		
		txtDocumento = new JTextField();
		txtDocumento.setColumns(10);
		txtDocumento.setBounds(204, 58, 131, 19);
		contentPanel.add(txtDocumento);
		
		JButton btnBorrar = new JButton("Eliminar");
		btnBorrar.setBounds(42, 132, 136, 21);
		contentPanel.add(btnBorrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(199, 132, 136, 21);
		contentPanel.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				txtDocumento.setText("");
				
			}
			
		});
		
		btnBorrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (txtDocumento.getText().equals("")) {
					Utilidades.msg(btnBorrar, "El DNI no puede estar vacío");
					txtDocumento.requestFocus();
					return;
				}
				
				if (!Utilidades.isNumInt(txtDocumento.getText())) {
	                Utilidades.msg(btnBorrar, "DNI incorrecto");
	                txtDocumento.setText("");
	                txtDocumento.requestFocus();
	                return;
	            }
	            
	            if (Utilidades.isNumInt(txtDocumento.getText()) && !(Integer.parseInt(txtDocumento.getText()) > 0)) {
	                Utilidades.msg(btnBorrar, "DNI incorrecto, debe ser mayor a 0");
	                txtDocumento.setText("");
	                txtDocumento.requestFocus();
	                return;                
	            }
				
				PacienteServicio pacienteServicio = new PacienteServicio();
				boolean borrado = pacienteServicio.borrarPaciente(Integer.valueOf(txtDocumento.getText()));
				
				if (borrado) {
					Utilidades.msg(btnBorrar, "Paciente borrado correctamente");
					dispose();
				} else {
					Utilidades.msg(btnBorrar, "Ocurrió un error al borrar el Paciente o el mismo no existe");
					dispose();
				}
				
			}
		});
		
	}
	
}
