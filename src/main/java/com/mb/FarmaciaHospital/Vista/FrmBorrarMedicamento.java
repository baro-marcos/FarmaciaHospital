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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

/**
*
* @author Marcos Baró
*/

public class FrmBorrarMedicamento extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIDMedicamento;
	private JTextField txtMedicamento;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FrmBorrarMedicamento dialog = new FrmBorrarMedicamento();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FrmBorrarMedicamento(JFrame parent) {
		
		// El segundo parametro 'true' hace que el JDialog sea modal
        super(parent, "Borrar Medicamento", true);
        
        setLocationRelativeTo(parent); // Centrar el JDialog sobre el JFrame

		setTitle("Borrar Medicamento");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				FrmBorrarMedicamento.class.getResource("/com/mb/FarmaciaHospital/Imagenes/medicamento_1616.png")));
		setResizable(false);
		setBounds(100, 100, 814, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JComboBox comboBoxMedicamentos = new JComboBox();
		comboBoxMedicamentos.setMaximumRowCount(20);
		comboBoxMedicamentos.setBounds(81, 22, 644, 21);
		contentPanel.add(comboBoxMedicamentos);

		JLabel lblID = new JLabel("ID Medicamento");
		lblID.setBounds(81, 102, 96, 13);
		contentPanel.add(lblID);

		txtIDMedicamento = new JTextField();
		txtIDMedicamento.setEditable(false);
		txtIDMedicamento.setColumns(10);
		txtIDMedicamento.setBounds(197, 99, 143, 19);
		contentPanel.add(txtIDMedicamento);

		JLabel lblMedicamento = new JLabel("Medicamento");
		lblMedicamento.setBounds(81, 154, 110, 13);
		contentPanel.add(lblMedicamento);

		txtMedicamento = new JTextField();
		txtMedicamento.setEditable(false);
		txtMedicamento.setColumns(10);
		txtMedicamento.setBounds(81, 180, 644, 19);
		contentPanel.add(txtMedicamento);

		JButton btnBorrar = new JButton("Eliminar");
		btnBorrar.setBounds(220, 278, 131, 21);
		contentPanel.add(btnBorrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(434, 278, 131, 21);
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
		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MedicamentoServicio medicamentoServicio = new MedicamentoServicio();
				boolean borrado = medicamentoServicio.borrarMedicamento(Long.valueOf(txtIDMedicamento.getText()));
				
				if (borrado) {
					Utilidades.msg(btnBorrar, "Medicamento borrado correctamente");
					dispose();
				} else {
					Utilidades.msg(btnBorrar, "Ocurrió un error al borrar el Medicamento");
					dispose();
				}
				
			}
		});

	}

}
