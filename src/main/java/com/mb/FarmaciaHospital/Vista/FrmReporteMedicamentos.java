package com.mb.FarmaciaHospital.Vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mb.FarmaciaHospital.Servicio.PacienteMedicamentoServicio;
import com.mb.FarmaciaHospital.Utilidad.Utilidades;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

/**
*
* @author Marcos Baró
*/

public class FrmReporteMedicamentos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private JTable tableMedicamentos;
    
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FrmReporteMedicamentos dialog = new FrmReporteMedicamentos();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FrmReporteMedicamentos(JFrame parent, List<Object[]> medicamentos) {
		
		// El segundo parametro 'true' hace que el JDialog sea modal
        super(parent, "Reporte Medicamentos", true);
        
        setLocationRelativeTo(parent); // Centrar el JDialog sobre el JFrame
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/medicamento_1616.png")));
        setResizable(false);
		
		setBounds(100, 100, 866, 640);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(679, 555, 142, 21);
		contentPanel.add(btnSalir);
		
		// Crear la JTable
        tableMedicamentos = new JTable();
        tableMedicamentos.setBounds(30, 88, 757, 433);
        
        // Crear un JScrollPane para agregar el scroll a la tabla
        JScrollPane scrollPane = new JScrollPane(tableMedicamentos);
        scrollPane.setBounds(30, 41, 791, 480);  // Establecer el mismo tamaño para que la tabla encaje
        contentPanel.add(scrollPane);
		
		// Crear y definir el modelo de la tabla
        final DefaultTableModel modelMedicamentos = new DefaultTableModel(
            null,
            new String[]{"Medicamento", "Cantidad"}) {  // Definir los títulos de las columnas

            // Celdas editables
            boolean[] canEdit = new boolean[]{false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return canEdit[colIndex];
            }
        };
        
        tableMedicamentos.setModel(modelMedicamentos);
        tableMedicamentos.getTableHeader().setReorderingAllowed(false);
        
        // Cargamos el data table
        PacienteMedicamentoServicio pacienteMedicamentoServicio = new PacienteMedicamentoServicio();
		Utilidades.limpiarTabla(tableMedicamentos, modelMedicamentos);
		pacienteMedicamentoServicio.llenarTablaReporte(medicamentos, modelMedicamentos);
        
        // Acción del botón "Salir"
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
		
	}
}
