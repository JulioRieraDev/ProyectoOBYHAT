package com.obyhat.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.obyhat.interfaces.CRUD;
import com.obyhat.modelo.conexion.Conexion;
import com.obyhat.modelo.dto.AsignarDTO;
import com.obyhat.modelo.dto.CategoriaDTO;

public class AsignarDAO implements CRUD<AsignarDTO>{

	private static final String SQL_INSERT = "INSERT INTO asignar (fechaAsignacion, cantidad) VALUES (?,?)";
	private static final String SQL_DELETE = "DELETE FROM asignar WHERE idOrdenSalida = ?";
	private static final String SQL_UPDATE = "UPDATE asignar SET idOrdenSalida = ?, fechaAsignacion = ?, cantidad = ? WHERE idOrdenSalida = ?";
	private static final String SQL_READ   = "SELECT fechaAsignacion, cantidad FROM asignar WHERE idOrdenSalida = ?";
	private static final String SQL_READALL= "SELECT fechaAsignacion, cantidad FROM asignar";
	
	private static final Conexion miConexion = Conexion.saberEstado();
	private PreparedStatement pStatement;
	private ResultSet res;
	
	
	@Override
	public boolean Ingresar(AsignarDTO datos) {

		try {
			
			// Los numeros representan mis signos de interrogacion.
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_INSERT);
			pStatement.setString(1, datos.getFechaAsignacion());
			pStatement.setInt(	 2, datos.getCantidadSeleccionada());
			
			// Retornar el valor boolean si esto se ejecuto.
			if (pStatement.executeUpdate() > 0) {
				
				//JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Procesado exitosamente.");
				return true;
			}
			
			pStatement.close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al registrar todo esto.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al registrar un nuev Usuario. \n"+e);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return false;
	}
	
	
	@Override
	public boolean Actualizar(AsignarDTO datos) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean Eliminar(Object key) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public AsignarDTO Consultar(Object key) {

		AsignarDTO asignar = null;
		
		try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READ);
			pStatement.setString(1, key.toString());
			
			res = pStatement.executeQuery();
			
			while (res.next()) {
				
				asignar = new AsignarDTO(res.getString(1),res.getInt(2)); 
			}
			
			return asignar;
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar la Categoria. \n"+e, null, JOptionPane.INFORMATION_MESSAGE);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return asignar;
	}
	@Override
	public List<AsignarDTO> ConsultarTodos() {

		ArrayList<AsignarDTO> asignar = new ArrayList<>();
		
		try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READALL);
			
			res = pStatement.executeQuery();
			
			while (res.next()) {
				
				asignar.add(new AsignarDTO(res.getString(1),res.getInt(2)));
			}
                        
                        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar todas las Categorias. \n"+e, null, JOptionPane.INFORMATION_MESSAGE);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return asignar;
	}
	
}
