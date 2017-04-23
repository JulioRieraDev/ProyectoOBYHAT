package com.obyhat.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.obyhat.interfaces.CRUD;
import com.obyhat.modelo.conexion.Conexion;
import com.obyhat.modelo.dto.CategoriaDTO;
import com.obyhat.modelo.dto.ObrasDTO;

public class CategoriaDAO implements CRUD<CategoriaDTO>{

	private static final String SQL_INSERT  = "INSERT INTO categoriaMaterial (nombre, descripcion) VALUES (?, ?)";
	private static final String SQL_UPDATE  = "UPDATE categoriaMaterial SET nombre = ?, descripcion = ? WHERE nombre = ?";
	private static final String SQL_DELETE  = "DELETE FROM categoriaMaterial WHERE nombre = ?";
	private static final String SQL_READ    = "SELECT * FROM categoriaMaterial WHERE nombre = ?";
	private static final String SQL_READALL = "SELECT * FROM categoriaMaterial";
	
	private static final Conexion miConexion = Conexion.saberEstado();
	private PreparedStatement pStatement;
	private ResultSet res;
	
	@Override
	public boolean Ingresar(CategoriaDTO datos) {
		
		try {
			
			// Los numeros representan mis signos de interrogacion.
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_INSERT);
			pStatement.setString(1, datos.getNombreCategoria());
			pStatement.setString(2, datos.getDesCategoria());
			
			// Retornar el valor boolean si esto se ejecuto.
			if (pStatement.executeUpdate() > 0) {
				
				JOptionPane.showMessageDialog(null, "Categoria registrada exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
				
				return true;
			}
			
			pStatement.close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al registrar una nueva Categoria. \n"+e, null, JOptionPane.INFORMATION_MESSAGE);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return false;
	}

	
	public boolean Actualizar(CategoriaDTO datos, String nombre) {

		try {
			
			// Los numeros representan mis signos de interrogacion.
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_UPDATE);
			pStatement.setString(1, datos.getNombreCategoria());
			pStatement.setString(2, datos.getDesCategoria());
			pStatement.setString(3, nombre);
			
			// Retornar el valor boolean si esto se ejecuto.
			if (pStatement.executeUpdate() > 0) {
				
				JOptionPane.showMessageDialog(null, "Categoria actualizada exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
			
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al actualizar la Categoria. \n"+e, null, JOptionPane.INFORMATION_MESSAGE);
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
				
				JOptionPane.showMessageDialog(null, "Categoria eliminada exitosamente.", null, JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar borrar la Categoria. \n"+e, null, JOptionPane.INFORMATION_MESSAGE);
		} finally {
			miConexion.Desconectar();
		}
		
		return false;
	}

	@Override
	public CategoriaDTO Consultar(Object key) {
		
		CategoriaDTO CA = null;
		
		try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READ);
			pStatement.setString(1, key.toString());
			
			res = pStatement.executeQuery();
			
			while (res.next()) {
				
				CA = new CategoriaDTO(res.getInt(1),res.getString(2),res.getString(3)); 
			}
			
			return CA;
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar la Categoria. \n"+e, null, JOptionPane.INFORMATION_MESSAGE);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return CA;
	}

	@Override
	public List<CategoriaDTO> ConsultarTodos() {

		ArrayList<CategoriaDTO> categorias = new ArrayList<>();
		
		try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READALL);
			
			res = pStatement.executeQuery();
			
			while (res.next()) {
				
				categorias.add(new CategoriaDTO(res.getInt(1),res.getString(2),res.getString(3)));
			}
                        
                        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar todas las Categorias. \n"+e, null, JOptionPane.INFORMATION_MESSAGE);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return categorias;
	}


	@Override
	public boolean Actualizar(CategoriaDTO datos) {
		// TODO Auto-generated method stub
		return false;
	}

}
