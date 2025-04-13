package com.mb.FarmaciaHospital.Vista;

import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mb.FarmaciaHospital.Entidad.Medicamento;
import com.mb.FarmaciaHospital.Servicio.MedicamentoServicio;
import com.mb.FarmaciaHospital.Utilidad.Utilidades;

import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmActualizarMedicamento extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIDMedicamento;
	private JTextField txtMedicamento;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FrmActualizarMedicamento dialog = new FrmActualizarMedicamento();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FrmActualizarMedicamento(JFrame parent) {
		
		// El segundo parametro 'true' hace que el JDialog sea modal
        super(parent, "Actualizar Medicamento", true);
        
        setLocationRelativeTo(parent); // Centrar el JDialog sobre el JFrame
		
		setTitle("Actualizar Medicamento");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/medicamento_1616.png")));
		setBounds(100, 100, 771, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox comboBoxMedicamentos = new JComboBox();
		comboBoxMedicamentos.setMaximumRowCount(20);
		comboBoxMedicamentos.setBounds(50, 59, 644, 21);
		contentPanel.add(comboBoxMedicamentos);
		
		JLabel lblID = new JLabel("ID Medicamento");
		lblID.setBounds(50, 139, 96, 13);
		contentPanel.add(lblID);
		
		JLabel lblMedicamento = new JLabel("Medicamento");
		lblMedicamento.setBounds(50, 191, 112, 13);
		contentPanel.add(lblMedicamento);
		
		txtIDMedicamento = new JTextField();
		txtIDMedicamento.setEditable(false);
		txtIDMedicamento.setBounds(166, 136, 143, 19);
		contentPanel.add(txtIDMedicamento);
		txtIDMedicamento.setColumns(10);
		
		txtMedicamento = new JTextField();
		txtMedicamento.setBounds(50, 217, 644, 19);
		contentPanel.add(txtMedicamento);
		txtMedicamento.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(189, 315, 131, 21);
		contentPanel.add(btnActualizar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(403, 315, 131, 21);
		contentPanel.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxMedicamentos.setSelectedIndex(0);
				txtIDMedicamento.setText("");
				txtMedicamento.setText("");
				
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
		
		btnActualizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (txtMedicamento.getText().equals("") || txtIDMedicamento.getText().equals("")) {
					Utilidades.msg(btnActualizar, "El ID y el nombre del medicamento no pueden estar vacíos");
					txtMedicamento.requestFocus();
					return;
				}
				
				MedicamentoServicio medicamentoServicio = new MedicamentoServicio();
				
				Medicamento medicamento = medicamentoServicio.actualizarMedicamento(Long.valueOf(txtIDMedicamento.getText()), 
						txtMedicamento.getText());
				
				if (medicamento != null) {
					
					Utilidades.msg(btnActualizar, "Medicamento actualizado correctamente");
					dispose();
					
				} else {
					
					Utilidades.msg(btnActualizar, "Ocurrió un error al Actualizar el Medicamento, intente nuevamente");
					dispose();
					
				}
				
			}
			
		});
		
	}
}
