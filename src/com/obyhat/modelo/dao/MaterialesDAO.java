
package com.obyhat.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.obyhat.interfaces.CRUD;
import com.obyhat.modelo.dto.CategoriaDTO;
import com.obyhat.modelo.dto.MaterialesDTO;
import com.obyhat.modelo.conexion.Conexion;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jafeht
 */
public class MaterialesDAO implements CRUD<MaterialesDTO>{
    
   
    
    private static final String SQL_INSERT = "INSERT INTO `material`(`nombreMaterial`, `cantidadMaterial`, `fechaRegistro`) VALUES (?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM material WHERE nombreMaterial = ?";
	private static final String SQL_UPDATE = "UPDATE `material` SET `nombreMaterial`=?,`cantidadMaterial`=?";
	private static final String SQL_READ   = "SELECT * FROM `material` WHERE nombreMaterial = ?; ";
	private static final String SQL_READALL= "SELECT * FROM `material`;";
    
    
    private static final Conexion miConexion = Conexion.saberEstado();
	
	private PreparedStatement pStatement = null;
	private ResultSet res = null;
    

    @Override
    public boolean Ingresar(MaterialesDTO datos) {
        try {
           
            // Los numeros representan mis signos de interrogacion.
            pStatement = miConexion.obtenerConexion().prepareStatement(SQL_INSERT);
            pStatement.setString(1, datos.getNombreMaterial());       
            pStatement.setInt(2, datos.getCantidadMaterial());
            pStatement.setString(3, datos.getFechaRegistro());
            
           
            // Retornar el valor boolean si esto se ejecuto.
            if (pStatement.executeUpdate() > 0) {
               
                JOptionPane.showMessageDialog(null, "Material registrado exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
               
                return true;
            }
           
            pStatement.close();
           
        } catch (SQLException e) {
           
            JOptionPane.showMessageDialog(null, "Error al registrar un nuevo material. \n"+e, null, JOptionPane.INFORMATION_MESSAGE);
        } finally {
           
            miConexion.Desconectar();
        }
       
        return false;
    }

    @Override
    public boolean Actualizar(MaterialesDTO datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Eliminar(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MaterialesDTO Consultar(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaterialesDTO> ConsultarTodos() {
        
    	ArrayList<MaterialesDTO> materiales = new ArrayList<>();
		
			try {
				
				pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READALL);
				res = pStatement.executeQuery();
				
				while (res.next()) {
					
					materiales.add(new MaterialesDTO(res.getString(2),res.getInt(3)));
				}
	                        
	                        
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Error al intentar consultar todos los materiales. \n"+e, null, JOptionPane.INFORMATION_MESSAGE);
			} finally {
			
				miConexion.Desconectar();
			}
		
		return materiales;
    }
    
}
