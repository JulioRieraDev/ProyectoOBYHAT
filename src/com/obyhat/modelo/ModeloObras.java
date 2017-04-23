package com.obyhat.modelo;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import com.obyhat.modelo.conexion.Conexion;
import com.obyhat.vista.paneles.PanelObras;

public class ModeloObras {

	private String nombreObra,encargado,telefono,direccion;
	
	private PanelObras pObras = new PanelObras();
	
	//private Conexion miConexion = Conexion.getInstance();
	private PreparedStatement ps;
	
	private String SQL_INSERT = "INSERT INTO `obras`(`idObra`,`nombreObra`, `encargadoObra`, `telefonoObra`, `direccionObra`) VALUES (?,?,?,?,?);";
	
	public void InsertObra() {
		
		try {
			
			/*miConexion = new Conexion();
			
				ps = miConexion.obtenerConexion().prepareStatement(SQL_INSERT);
				ps.setInt(1, 0);
				ps.setString(2, pObras.getTxtNombreObra().getText());
				ps.setString(3, pObras.getTxtEncargadoObra().getText());
				ps.setString(4, pObras.getTxtTelefonoObra().getText());
				ps.setString(5, pObras.getTxtDireccionObra().getText());
				
				*/ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Obra registrada exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			
			System.out.printf("Error al guardar una nueva obra"+e);
		}
	}
}
