
package com.obyhat.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.obyhat.interfaces.CRUD;

import com.obyhat.modelo.conexion.Conexion;
import com.obyhat.modelo.dto.ProveedorDTO;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jafeht
 */
public class ProveedorDAO implements CRUD<ProveedorDTO>{
    
   
    
    private static final String SQL_INSERT = "INSERT INTO `proveedor`(`rif`, `razonSocial`, `telefono`, `direccion`,`materialProvee`, `email`) VALUES (?,?,?,?,?,?)";
        private static final String SQL_DELETE = "DELETE FROM proveedor WHERE rif = ?";
	private static final String SQL_UPDATE = "UPDATE `material` SET `nombreMaterial`=?,`cantidadMaterial`=?";
	private static final String SQL_READ   = "SELECT * FROM `material` WHERE nombreMaterial = ?; ";
	private static final String SQL_READALL= "SELECT `rif`, `razonSocial`, `telefono`, `direccion`,`materialProvee`, `email` FROM `proveedor`"; 
    
    
    private static final Conexion miConexion = Conexion.saberEstado();
	
	private PreparedStatement pStatement = null;
	private ResultSet res = null;

    @Override
    public boolean Ingresar(ProveedorDTO datos) {
        try {

            // Los numeros representan mis signos de interrogacion.
            pStatement = miConexion.obtenerConexion().prepareStatement(SQL_INSERT);
            pStatement.setString(1, datos.getRif());
            pStatement.setString(2, datos.getRazonSocial());
            pStatement.setString(3, datos.getTelefono());
            pStatement.setString(4, datos.getDireccion());
            pStatement.setString(5, datos.getMaterialProvee());
            pStatement.setString(6, datos.getEmail());
            // Retornar el valor boolean si esto se ejecuto.
            if (pStatement.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(null, "Proveedor registrado exitosamente", null, JOptionPane.INFORMATION_MESSAGE);

                return true;
            }

            pStatement.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al registrar un nuevo proveedor. \n" + e, null, JOptionPane.INFORMATION_MESSAGE);
        } finally {

            miConexion.Desconectar();
        }

        return false;
    }

    @Override
    public boolean Actualizar(ProveedorDTO datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Eliminar(Object key) {
        try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_DELETE);
			pStatement.setString(1, key.toString());
			
			if (pStatement.executeUpdate() > 0) {
				
				JOptionPane.showMessageDialog(null, "Obra eliminada exitosamente.", null, JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Obra eliminada exitosamente.");
				return true;
			}
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar borrar la obra.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al intentar borrar la obra. \n"+e);
		} finally {
			miConexion.Desconectar();
		}
		
		return false;
    }

    @Override
    public ProveedorDTO Consultar(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProveedorDTO> ConsultarTodos() {
        ArrayList<ProveedorDTO> proveedor = new ArrayList<>();
		
		try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READALL);
			
			res = pStatement.executeQuery();
			
			while (res.next()) {
				
				proveedor.add(new ProveedorDTO(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6)));
			}
                        
                        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar todos los materiales. \n"+e, null, JOptionPane.INFORMATION_MESSAGE);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return proveedor;
    }
    

    
    
}
