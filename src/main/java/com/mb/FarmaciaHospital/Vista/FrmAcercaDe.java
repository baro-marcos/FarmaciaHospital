package com.mb.FarmaciaHospital.Vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class FrmAcercaDe extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FrmAcercaDe dialog = new FrmAcercaDe();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FrmAcercaDe(JFrame parent) {
		
		// El segundo parametro 'true' hace que el JDialog sea modal
        super(parent, "Acerca de...", true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/info_1616.png")));
        setResizable(false);
        
        setLocationRelativeTo(parent); // Centrar el JDialog sobre el JFrame
	
		setBounds(100, 100, 592, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(433, 111, 139, 29);
		contentPanel.add(btnAceptar);
		
		JLabel lblAcercaDe = new JLabel("Sistema realizado por Baró Marcos para el control de \r\nMedicamentos a Pacientes.");
		lblAcercaDe.setFont(new Font("Courier New", Font.BOLD, 12));
		lblAcercaDe.setBounds(10, 31, 562, 56);
		contentPanel.add(lblAcercaDe);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		
	}
}
