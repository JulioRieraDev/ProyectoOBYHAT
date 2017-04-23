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
import com.obyhat.modelo.dto.UsuarioDTO;

public class UsuarioDAO implements CRUD<UsuarioDTO>{

	private static final String SQL_INSERT = "INSERT INTO usuario (nombre, password, idTipo) VALUES (?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM usuario WHERE nombre = ?";
	private static final String SQL_UPDATE = "UPDATE usuario SET nombre = ?, password = ?, idTipo =? WHERE nombre = ?";
	private static final String SQL_READ   = "SELECT * FROM usuario WHERE nombre = ?";
	private static final String SQL_READALL= "SELECT usuario.nombre, tipo_de_usuario.nombre FROM usuario INNER JOIN tipo_de_usuario USING(idTipo) ORDER BY idTipo";
	private static final String SQL_READALL_T_USUARIOS= "SELECT idTipo, nombre FROM tipo_de_usuario ORDER BY idTipo";
	
	private static final Conexion miConexion = Conexion.saberEstado();
	private PreparedStatement pStatement;
	private ResultSet res;
	
	@Override
	public boolean Ingresar(UsuarioDTO datos) {

		try {
			
			// Los numeros representan mis signos de interrogacion.
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_INSERT);
			pStatement.setString(1, datos.getNombre());
			pStatement.setString(2, datos.getContrasena());
			pStatement.setInt(   3, datos.getIdTipoUsuario());
			
			// Retornar el valor boolean si esto se ejecuto.
			if (pStatement.executeUpdate() > 0) {
				
				JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Usuario registrado exitosamente.");
				return true;
			}
			
			pStatement.close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al registrar un nuevo Usuario.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al registrar un nuev Usuario. \n"+e);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return false;
	}


	public boolean Actualizar(UsuarioDTO datos, String nombre) {

		try {
			
			// Los numeros representan mis signos de interrogacion.
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_UPDATE);
			pStatement.setString(1, datos.getNombre());
			pStatement.setString(2, datos.getContrasena());
			pStatement.setInt(   3, datos.getIdTipoUsuario());
			pStatement.setString(4, nombre);
			
			// Retornar el valor boolean si esto se ejecuto.
			if (pStatement.executeUpdate() > 0) {
				
				JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Usuario actualizado exitosamente.");
				return true;
			}
			
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al actualizar el Usuario.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al actualizar el Usuario. \n"+e);
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
				
				JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.", null, JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Usuario eliminado exitosamente.");
				return true;
			}
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar borrar el Usuario.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al intentar borrar el Usuario. \n"+e);
		} finally {
			miConexion.Desconectar();
		}
		
		return false;
	}

	@Override
	public UsuarioDTO Consultar(Object key) {

		UsuarioDTO usuario = null;
		try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READ);
			pStatement.setString(1, key.toString());
			
			res = pStatement.executeQuery();
			
			while (res.next()) {
				usuario = new UsuarioDTO(res.getString(2),res.getString(3),res.getInt(4)); 
				
			}
			return usuario;
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar el Usuario.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al intentar consultar el Usuario. \n"+e);
		} finally {
			miConexion.Desconectar();
		}
		
		return usuario;
	}

	@Override
	public List<UsuarioDTO> ConsultarTodos() {

		ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
		
		try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READALL);
			
			res = pStatement.executeQuery();
			
			while (res.next()) {
				
				usuarios.add(new UsuarioDTO(res.getString(1),res.getString(2)));
			}
                        
                        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar todos los Usuario.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al intentar consultar todos los Usuario. \n"+e);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return usuarios;
	}

	public List<UsuarioDTO> obtenerTUsuarios() {

		ArrayList<UsuarioDTO> tipodeUsuarios = new ArrayList<>();
		
		try {
			
			pStatement = miConexion.obtenerConexion().prepareStatement(SQL_READALL_T_USUARIOS);
			
			res = pStatement.executeQuery();
			
			while (res.next()) {
				
				tipodeUsuarios.add(new UsuarioDTO(res.getInt(1),res.getString(2)));
			}
                        
                        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar todos los tipos de usuarios.", null, JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Error al intentar consultar todos los tipos de usuarios. \n"+e);
		} finally {
			
			miConexion.Desconectar();
		}
		
		return tipodeUsuarios;
	}
	
	
	@Override
	public boolean Actualizar(UsuarioDTO datos) {
		// TODO Auto-generated method stub
		return false;
	}

}
