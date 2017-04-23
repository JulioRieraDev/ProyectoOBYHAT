package com.obyhat.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.obyhat.modelo.dao.CategoriaDAO;
import com.obyhat.modelo.dao.UsuarioDAO;
import com.obyhat.modelo.dto.CategoriaDTO;
import com.obyhat.modelo.dto.UsuarioDTO;
import com.obyhat.vista.paneles.PanelCategorias;
import com.obyhat.vista.paneles.PanelUsuarios;

public class BotonesUsuario implements ActionListener {

	private PanelUsuarios PU;
	private UsuarioDAO miUsuario;
	private String nombre;
	
	
	public BotonesUsuario(PanelUsuarios PU) {
		
		this.PU = PU;
		this.miUsuario = new UsuarioDAO();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.PU.getBtnAgregar()) {

			System.out.println("Boton 'Agregar Obra' escuchando Rock!");
			
			this.registrarUsuario();
			this.obtenerUsuarios();
		}

		if (e.getSource() == this.PU.getBtnActualizar()) {

			System.out.println("Boton Actualizar Escuchando");
            
       	 	this.actualizarUsuario();
       	 	this.PU.getBtnAgregar().setEnabled(true);
      	  	this.PU.getBtnActualizar().setEnabled(false);
		}

		if (e.getSource() == this.PU.getBtnCancelar()) {

			System.out.println("Boton Cancelar Escuchando");
            
			this.PU.limpiar();
			this.obtenerUsuarios();
			this.PU.getBtnAgregar().setEnabled(true);
			this.PU.getBtnActualizar().setEnabled(false);
		}

		if (e.getSource() == this.PU.getBtnBuscar()) {
			
			System.out.println("Boton Buscar Escuchando");
            
        	this.obtenerUsuarios();  
        	
        	// Llenar el combo de tipo de usuarios.
        	this.obtenerTiposdeUsuarios();
		}

		if (e.getSource() == this.PU.getBtnModificar()) {

			System.out.println("Boton 'Modificar' escuchando Rock!");
			
			eveModificar();
		}

		if (e.getSource() == this.PU.getBtnEliminar()) {

			System.out.println("Boton 'Eliminar' escuchando Rock!");
			
			this.eliminarUsuario();
		}
		
	}
	
	public void registrarUsuario() {
		
		try {
				
				if (ValidarDatos().equals("")) {
					/*
					 *  Si el medoto ValidarDatos devuelve "", es decir, 
					 *  nada es porque los dos campos estan llenos. 
					 *  
					 */
					
					try {
						
						this.miUsuario.Ingresar(this.PU.ObtenerDatos());
						this.PU.limpiar();
						
					} catch (Exception e2) {
						
						System.out.println("Error al insertar "+e2);
					}
				}
				
				else{
					/*
					 * de lo contrario emite el mensaje
					 */
		            
	                JOptionPane.showMessageDialog(null, "ERROR!! \n" + ValidarDatos(), "Validando Datos",
	                    JOptionPane.ERROR_MESSAGE);
	            }
				
		} catch(Exception e1){
	           
	            JOptionPane.showMessageDialog(null, "Error en Eventos del Boton Agregar. \n" + e1, "Error",
	                    JOptionPane.ERROR_MESSAGE);
	      }
	}
	
	public void actualizarUsuario() {
		
		try {
			
			if (ValidarDatos().equals("")) {
				/*
				 *  Si el medoto ValidarDatos devuelve "", es decir, 
				 *  nada es porque los dos campos estan llenos. 
				 *  
				 */
				
				try {
					
					miUsuario.Actualizar(this.PU.ObtenerDatos(),this.getNombre(nombre));
					PU.limpiar();
					
					this.obtenerUsuarios();
					
				} catch (Exception e2) {
					
					System.out.println("Error al insertar "+e2);
				}
			}
			
			else{
				/*
				 * de lo contrario emite el mensaje
				 */
	            
                JOptionPane.showMessageDialog(null, "ERROR!! \n" + ValidarDatos(), "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
            }
			
		} catch(Exception e1){
           
            JOptionPane.showMessageDialog(null, "Error en Eventos del Boton Agregar. \n" + e1, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void obtenerUsuarios() {
		
		List<UsuarioDTO> usuarios = this.miUsuario.ConsultarTodos();
    	
    	PU.limpiarTabla();
        
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(usuarios.get(i).getNombre()+" - "+usuarios.get(i).getTipo_de_Usuario());
            this.PU.insertarTabla(usuarios.get(i).toArray());
        }
	}
	
	public void eveModificar() {
		

		//Obtenet la Fila seleccionada.
        int filaSeleccionada = PU.getTablaUsuarios().getSelectedRow();
        
            if (filaSeleccionada >= 0) {

            	String nombreUsuario = PU.getTablaUsuarios().getValueAt(filaSeleccionada, 0).toString();
                
            	int opc = JOptionPane.showConfirmDialog(null, " Desea modificar el usuario: "+nombreUsuario+"? ", "Confirmar modificacion ",
            	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                /*
            	 * Le preguntare al usuario si en realidad desea modificar la categoria.
            	 */

                if (opc == 0) {
                
	                try {
						
	                	PU.getModeloTabla().removeRow(filaSeleccionada);
	                	UsuarioDTO consultarUsuario = miUsuario.Consultar(nombreUsuario);
	                	PU.llenarFormulario(consultarUsuario);
	                	
	                	PU.getBtnAgregar().setEnabled(false);
	                	PU.getBtnActualizar().setEnabled(true);
	                	
	                	nombre = consultarUsuario.getNombre();
	                	
	                	this.getNombre(nombre);
	                	
	                	
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(null, "La cedula no existe", "Informacion", JOptionPane.INFORMATION_MESSAGE);
					}
                }
            }
                
            else{
            
                JOptionPane.showMessageDialog(null, "Seleccione la fila a eliminar");
            }
	}
	
	public void eliminarUsuario() {
		
		int filaSelecionada = PU.getTablaUsuarios().getSelectedRow();
		
		if(filaSelecionada != -1){

            String nombreUsuario = PU.getTablaUsuarios().getValueAt(filaSelecionada, 0).toString();
     
            int opc = JOptionPane.showConfirmDialog(null, " Desea eliminar de forma permanente al usuario: "+nombreUsuario+"? ", "Confirmar eliminacion ",
        	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            /*
        	 * Le preguntare al usuario si en realidad desea modificar la categoria.
        	 */

            if (opc == 0) {
              
            	try {
					
	            	PU.getModeloTabla().removeRow(filaSelecionada);
	            	this.miUsuario.Eliminar(nombreUsuario);
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "La cedula no existe", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				}
            }  
		}
		
		else{
			
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }
	}
	
	public void obtenerTiposdeUsuarios() {
		
		List<UsuarioDTO> tipoUsuarios = this.miUsuario.obtenerTUsuarios();
        
		PU.vaciar_comboUsuario();
		
        for (int i = 0; i < tipoUsuarios.size(); i++) {
            System.out.println(tipoUsuarios.get(i).getTipo_de_Usuario());
            PU.llenar_comboUsuario(tipoUsuarios.get(i).getTipo_de_Usuario());
        }
	}
	
	private String getNombre(String nombre) {
		
		return nombre; 	
	}
	
	public String ValidarDatos() {//Metodo para comprobar que los datos esten completos.
        
		String msj = "";
		
        if (this.PU.ObtenerDatos().getNombre().equals("")) {//Si TxtNombreMat esta vacio.
            msj += "Por favor ingrese un nombre de usuario. \n";
        }
        if (this.PU.ObtenerDatos().getContrasena().equals("")) {//Si TxtNombreMat esta vacio.
            msj += "Por favor ingrese la contrasena para su usuario. \n";
        }
        if (this.PU.ObtenerDatos().getIdTipoUsuario() == 1) {//Si TxtNombreMat esta vacio.
            msj += "Por favor seleccione un tipo de usuario. \n";
        }
        
        return msj;//devuelve msj.
    }
}
