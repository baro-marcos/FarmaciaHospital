package com.mb.FarmaciaHospital.Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mb.FarmaciaHospital.Servicio.MedicamentoServicio;
import com.mb.FarmaciaHospital.Utilidad.Utilidades;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
*
* @author Marcos Baró
*/

public class FrmCargaMedicamento extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtMedicamento;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FrmCargaMedicamento dialog = new FrmCargaMedicamento();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FrmCargaMedicamento(JFrame parent) {
		
		// El segundo parametro 'true' hace que el JDialog sea modal
        super(parent, "Carga Medicamento", true);
        
        setLocationRelativeTo(parent); // Centrar el JDialog sobre el JFrame
	
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCargaMedicamento.class.getResource("/com/mb/FarmaciaHospital/Imagenes/medicamento_1616.png")));
		setResizable(false);
		setTitle("Carga de Medicamento");
		setBounds(100, 100, 690, 242);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblMedicamento = new JLabel("Medicamento:");
		lblMedicamento.setBounds(41, 70, 86, 13);
		contentPanel.add(lblMedicamento);
		
		txtMedicamento = new JTextField();
		txtMedicamento.setBounds(135, 67, 508, 19);
		contentPanel.add(txtMedicamento);
		txtMedicamento.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(207, 133, 131, 21);
		contentPanel.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(421, 133, 131, 21);
		contentPanel.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtMedicamento.setText("");
				
			}
		});
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if (txtMedicamento.getText().equals("")) {
						Utilidades.msg(btnAgregar, "El nombre del medicamento no puede estar vacío");
						txtMedicamento.requestFocus();
						return;
					}
					
					MedicamentoServicio medicamentoServicio = new MedicamentoServicio();
					
					medicamentoServicio.insertarMedicamento(txtMedicamento.getText());
					
					Utilidades.msg(btnAgregar, "Medicamento ingresado correctamente");
					
					txtMedicamento.setText("");
					txtMedicamento.requestFocus();
					
					//dispose();
					
				} catch (Exception e1) {
					
					Utilidades.msg(btnAgregar, "Ocurrió un error al ingresar el Medicamento, intente nuevamente");
					dispose();
					
				}
				
			}
		});
		
	}

}
