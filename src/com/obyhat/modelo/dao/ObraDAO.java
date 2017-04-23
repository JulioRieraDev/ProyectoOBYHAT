package com.obyhat.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.obyhat.interfaces.CRUD;
import com.obyhat.modelo.conexion.Conexion;
import com.obyhat.modelo.dto.ObrasDTO;
import static com.obyhat.interfaces.Constantes.CERO;

public class ObraDAO implements CRUD<ObrasDTO> {

	// Static: Para usarlo sin instanciar.
	// final: Porque no se modificara.
	private static final String SQL_INSERT = "INSERT INTO obra (nombre, encargado, telefono, direccion) VALUES (?,?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM obra WHERE nombre = ?";
	private static final String SQL_UPDATE = "UPDATE `obra` SET `nombre`=?,`encargado`=?,`telefono`=?,`direccion`=? WHERE nombre = ?";
	private static final String SQL_READ   = "SELECT * FROM `obra` WHERE nombre = ?; ";
	private static final String SQL_READALL= "SELECT * FROM `obra`;"; 
    private static final String SQL_AUTO_BUSQUEDA = "SELECT * FROM `obra` WHERE nombre like '%?%'; ";
	
	// Obteniendo el estado de la conexion, haciendo uso del patron
	// Singelton.
	private static final Conexion miConexion = Conexion.saberEstado();
	// Me permitira trabajar con los signos de interrogacion.
	private PreparedStatement pStatement = null;
	private ResultSet res = null;

	
	@Override
	public boolean Ingresar(ObrasDTO datos) {
		
		try {
			
			// Los numeros representan mis signos de interrogacion.
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_INSERT);
			pStatement.setString(1, datos.getNombreObra());
			pStatement.setString(2, datos.getEncargadoObra());
			pStatement.setString(3, datos.getTelefonoObra());
			pStatement.setString(4, datos.getDireccionObra());
			
			// Retornar el valor boolean si esto se ejecuto.
			if (pStatement.executeUpdate() > 0) {
				
				JOptionPane.showMessageDialog(null, "Obra registrada exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Obra registrada exitosamente.");
				return true;
			}
			
			pStatement.close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al registrar una nueva obra.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al registrar una nueva obra. \n"+e);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return false;
	}
	
	
	
	public boolean Actualizar(ObrasDTO datos, String nombre) {

		try {
			
			// Los numeros representan mis signos de interrogacion.
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_UPDATE);
			pStatement.setString(1, datos.getNombreObra());
			pStatement.setString(2, datos.getEncargadoObra());
			pStatement.setString(3, datos.getTelefonoObra());
			pStatement.setString(4, datos.getDireccionObra());
			pStatement.setString(5, nombre);
			
			// Retornar el valor boolean si esto se ejecuto.
			if (pStatement.executeUpdate() > 0) {
				
				JOptionPane.showMessageDialog(null, "Obra actualizada exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Obra actualizada exitosamente.");
				return true;
			}
			
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al actualizar la obra.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al actualizar la obra. \n"+e);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return false;
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
	public ObrasDTO Consultar(Object key) {
		
		ObrasDTO OB = null;
		
		try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READ);
			pStatement.setString(1, key.toString());
			
			res = pStatement.executeQuery();
			
			while (res.next()) {
				OB = new ObrasDTO(res.getInt(1),res.getString(2),res.getString(3),
						res.getString(4),res.getString(5)); 
				
			}
			return OB;
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar la obra.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al intentar consultar la obra. \n"+e);
		} finally {
			miConexion.Desconectar();
		}
		
		return OB;
	}

	

	@Override
	public List<ObrasDTO> ConsultarTodos() {
		
		ArrayList<ObrasDTO> obras = new ArrayList<>();
		
		try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READALL);
			
			res = pStatement.executeQuery();
			
			while (res.next()) {
				
				obras.add(new ObrasDTO(res.getInt(1),res.getString(2),res.getString(3),
						res.getString(4),res.getString(5)));
			}
                        
                        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar todas las obras.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al intentar consultar todas las obra. \n"+e);
		} finally {
			miConexion.Desconectar();
		}
		
		return obras;
		
	}
        
        public List<ObrasDTO> autoBusqueda(Object key){
        
            ArrayList<ObrasDTO> obras = new ArrayList<>();
            
            try {
                
                pStatement = miConexion.obtenerConexion().prepareStatement("SELECT * FROM `obra` WHERE nombre like '%"+key+"%'");
               
		res = pStatement.executeQuery();
                
                while (res.next()) {                    
                    
                    obras.add(new ObrasDTO(res.getInt(1),res.getString(2),res.getString(3),
						res.getString(4),res.getString(5)));
                }
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, "Error al intentar consultar las obras.", null, JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Error al intentar consultar las obra. \n"+e);
            } finally{
            
                miConexion.Desconectar();
            }
            
            return obras;
        }



		@Override
		public boolean Actualizar(ObrasDTO datos) {
			// TODO Auto-generated method stub
			return false;
		}

	
}
