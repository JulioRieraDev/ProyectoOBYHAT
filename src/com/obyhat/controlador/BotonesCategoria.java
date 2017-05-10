package com.obyhat.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.obyhat.modelo.dao.CategoriaDAO;
import com.obyhat.modelo.dto.CategoriaDTO;
import com.obyhat.modelo.dto.ObrasDTO;
import com.obyhat.vista.paneles.PanelCategorias;
import com.obyhat.vista.principal.VistaPrincipal;

public class BotonesCategoria implements ActionListener {

	private PanelCategorias PC;
	private CategoriaDAO miCategoria;
	private String nombre;
	
	
	public BotonesCategoria(PanelCategorias PC) {
		
		this.PC = PC;
		this.miCategoria = new CategoriaDAO();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.PC.getBtnAgregar()) {
			
			System.out.println("Boton 'Agregar Obra' escuchando Rock!");
			
			registrarObra();
			this.obtenerCategorias();
		}

		if (e.getSource() == this.PC.getBtnActualizar()) {
			
			System.out.println("Boton Actualizar Escuchando");
            
       	 	this.actualizarCat();
       	 	this.PC.getBtnAgregar().setEnabled(true);
      	  	this.PC.getBtnActualizar().setEnabled(false);
		}

		if (e.getSource() == this.PC.getBtnCancelar()) {
			
			System.out.println("Boton Cancelar Escuchando");
            
			this.PC.limpiar();
			this.obtenerCategorias();
			this.PC.getBtnAgregar().setEnabled(true);
			this.PC.getBtnActualizar().setEnabled(false);
		}

		if (e.getSource() == this.PC.getBtnBuscar()) {
			
			System.out.println("Boton Buscar Escuchando");
            
        	this.obtenerCategorias();  
		}

		if (e.getSource() == this.PC.getBtnModificar()) {
			
			System.out.println("Boton 'Modificar' escuchando Rock!");
			
			eveModificar();
		}

		if (e.getSource() == this.PC.getBtnEliminar()) {
			
			System.out.println("Boton 'Eliminar' escuchando Rock!");
			
			this.eliminarCat();
		}
		
	}

	
	public void registrarObra() {
		
		try {
				
				if (ValidarDatos().equals("")) {
					/*
					 *  Si el medoto ValidarDatos devuelve "", es decir, 
					 *  nada es porque los dos campos estan llenos. 
					 *  
					 */
					
					try {
						
						this.miCategoria.Ingresar(this.PC.ObtenerDatos());
						this.PC.limpiar();
						
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
	
	public void actualizarCat() {
		
		try {
			
			if (ValidarDatos().equals("")) {
				/*
				 *  Si el medoto ValidarDatos devuelve "", es decir, 
				 *  nada es porque los dos campos estan llenos. 
				 *  
				 */
				
				try {
					
					miCategoria.Actualizar(this.PC.ObtenerDatos(),this.getNombre(nombre));
					PC.limpiar();
					
					this.obtenerCategorias();
					
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
	
	public void obtenerCategorias() {
		
		List<CategoriaDTO> categorias = this.miCategoria.ConsultarTodos();
    	
    	PC.limpiarTabla();
        
        for (int i = 1; i < categorias.size(); i++) {
            System.out.println(categorias.get(i).getNombreCategoria());
            this.PC.insertarTabla(categorias.get(i).toArray());
        }
	}
	
	public void eveModificar() {
			
			//Obtenet la Fila seleccionada.
		int filaSeleccionada = PC.getTablaCategoria().getSelectedRow();
	        
			if (filaSeleccionada >= 0) {

				String nombreObra = PC.getTablaCategoria().getValueAt(filaSeleccionada, 0).toString();

				int opc = JOptionPane.showConfirmDialog(null, " ¿Desea modificar la categoria: "+nombreObra+"? ", "Confirmar modificacion  ",
	        			  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				/*
				 * Le preguntare al usuario si en realidad desea modificar la categoria.
				 */
	        		
				if (opc == 0) {
	            	
	                try {
						
	                	PC.getModeloTabla().removeRow(filaSeleccionada);
	                	CategoriaDTO consultarCat = miCategoria.Consultar(nombreObra);
	                	PC.llenarFormulario(consultarCat);
	                	
	                	PC.getBtnAgregar().setEnabled(false);
	                	PC.getBtnActualizar().setEnabled(true);
	                	
	                	nombre = consultarCat.getNombreCategoria();
	                	
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
	
	public void eliminarCat() {
		
		int filaSelecionada = PC.getTablaCategoria().getSelectedRow();
		
		if(filaSelecionada != -1){

            String nombreCat = PC.getTablaCategoria().getValueAt(filaSelecionada, 0).toString();
            
            int opc = JOptionPane.showConfirmDialog(null, " Desea eliminar la categoria: "+nombreCat+"? ", "Confirmar eliminacion  ",
        	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            /*
        	 * Le preguntare al usuario si en realidad desea eliminar la categoria.
        	 */

            if (opc == 0) {
              
	            try {
					
	            	PC.getModeloTabla().removeRow(filaSelecionada);
	            	this.miCategoria.Eliminar(nombreCat);
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "La cedula no existe", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				}
            }
		}
		
		else{
			
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }
	}
	
	private String getNombre(String nombre) {
		
		return nombre; 	
	}
	
	public String ValidarDatos() {//Metodo para comprobar que los datos esten completos.
        
		String msj = "";
		
        if (this.PC.ObtenerDatos().getNombreCategoria().equals("")) {//Si TxtNombreMat esta vacio.
            msj += "Por favor ingrese el nombre de la categoria. \n";
        }
        
        return msj;//devuelve msj.
    }
}
