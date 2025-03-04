package com.mb.FarmaciaHospital.Utilidad;

import java.awt.Component;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mb.FarmaciaHospital.Entidad.PacienteMedicamento;

/**
*
* @author Marcos Baró
*/

public class Utilidades {
	
	public static boolean isNumInt(String cadena) {
		
        try {
            Integer.valueOf(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
        
    }

    public static String formatDate(Date fecha) {

        if (fecha == null) {
            fecha = new Date();
        }

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        return formatoDelTexto.format(fecha);
        
    }
    
    public static void msg(Component comp, String cadena) {
    	
        String mensaje = cadena;
        JOptionPane.showMessageDialog(comp, mensaje);
        
    }
    
    public static void limpiarTabla(JTable jTable, DefaultTableModel model) {
    	
        for (int i = 0; i < jTable.getRowCount(); i++) {
            model.removeRow(i);
            i -= 1;
        }
        
    }
    
    public static void llenarTabla(List<Object> listaObjetos, int cantidadCols, DefaultTableModel model) {
		
    	for (Object objeto : listaObjetos) {
    		
            Object[] fila = new Object[cantidadCols];
            
            // Obtener los campos de la clase del objeto usando reflexión
            Field[] campos = objeto.getClass().getDeclaredFields();
            
            for (int i = 0; i < cantidadCols; i++) {
                try {
                    campos[i].setAccessible(true);  // Aseguramos que los campos privados sean accesibles
                    fila[i] = campos[i].get(objeto); // Obtener el valor del campo del objeto
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            
            model.addRow(fila);  // Agregar la fila al modelo de la tabla
        }
		
	}

}
