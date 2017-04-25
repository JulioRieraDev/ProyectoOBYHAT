package com.obyhat.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.obyhat.modelo.ModeloObras;
import com.obyhat.modelo.dao.AsignarDAO;
import com.obyhat.modelo.dao.ObraDAO;
import com.obyhat.modelo.dto.ObrasDTO;
import com.obyhat.vista.paneles.PanelObras;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BotonesObras implements ActionListener {

	private PanelObras PO;
	private ObraDAO miObraDAO;
	private BotonesAsignar asignar;
	private String nombre;
	
	
	public BotonesObras(PanelObras PO) {
		
		this.PO = PO;
		this.miObraDAO = new ObraDAO();
		//this.asignar = new BotonesAsignar(null);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	/*********************************************************************/
		
		
		if (e.getSource() == PO.getBtnAgregar()) {
			
			System.out.println("Boton 'Agregar Obra' escuchando Rock!");
		
			this.registrarObra();
			this.PO.limpiar();
			this.getObras();
		}
		
		
	/*********************************************************************/
		
        
        if (e.getSource() == PO.getBtnActualizar()) {
                
        	System.out.println("Boton Actualizar Escuchando");
                    
        	 this.setObra();
        	 this.PO.getBtnAgregar().setEnabled(true);
       	  	 this.PO.getBtnActualizar().setEnabled(false);
        }
                
        
    /*********************************************************************/
		
        
        if (e.getSource() == PO.getBtnCancelar()) {
                
        	System.out.println("Boton Cancelar Escuchando");
                    
        	  this.PO.limpiar();
        	  this.getObras();
        	  this.PO.getBtnAgregar().setEnabled(true);
        	  this.PO.getBtnActualizar().setEnabled(false);
        }
                
		
	/*********************************************************************/
		
        
        if (e.getSource() == PO.getBtnBuscar()) {
                
        	System.out.println("Boton Buscar Escuchando");
                    
        	this.getObras();  
        }
                
        
    /*********************************************************************/
		
        
        if (e.getSource() == PO.getBtnAsignaciones()) {
                
        	System.out.println("Boton Buscar Escuchando");
                    
        	//System.out.println(getNombre(nombre));
                    
        }
                
    /*********************************************************************/
		
		
		if (e.getSource() == PO.getBtnModificar()) {
			
			System.out.println("Boton 'Modificar' escuchando Rock!");
			
			//Obtenet la Fila seleccionada.
            int filaSeleccionada = PO.getTablaObras().getSelectedRow();
            
                if (filaSeleccionada >= 0) {

                	String nombreObra = PO.getTablaObras().getValueAt(filaSeleccionada, 0).toString();
                    

                	int opc = JOptionPane.showConfirmDialog(null, " Desea modificar la obra: "+nombreObra+"? ", "Confirmar modificacion ",
                	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    /*
                	 * Le preguntare al usuario si en realidad desea modificar la categoria.
                	 */

                    if (opc == 0) {
                    
	                    try {
	    					
	                    	PO.getModeloTabla().removeRow(filaSeleccionada);
	                    	ObrasDTO consultarObra = miObraDAO.Consultar(nombreObra);
	                    	PO.llenarFormulario(consultarObra);
	                    	
	                    	PO.getBtnAgregar().setEnabled(false);
	                    	PO.getBtnActualizar().setEnabled(true);
	                    	
	                    	nombre = consultarObra.getNombreObra();
	                    	
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

		
	/*********************************************************************/
		
		
		if (e.getSource() == PO.getBtnEliminar()) {
	
			System.out.println("Boton 'Eliminar' escuchando Rock!");
			
			int filaSelecionada = PO.getTablaObras().getSelectedRow();
			
			if(filaSelecionada != -1){

                String nombreObra = PO.getTablaObras().getValueAt(filaSelecionada, 0).toString();
                

            	int opc = JOptionPane.showConfirmDialog(null, " Desea eliminar permanentemente la obra: "+nombreObra+"? ", "Confirmar eliminacion ",
            	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                /*
            	 * Le preguntare al usuario si en realidad desea modificar la categoria.
            	 */

                if (opc == 0) {
                
	                try {
						
	                	PO.getModeloTabla().removeRow(filaSelecionada);
	                	this.miObraDAO.Eliminar(nombreObra);
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(null, "La cedula no existe", "Informacion", JOptionPane.INFORMATION_MESSAGE);
					}
                }    
			}
			else{
				
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            }
		}
		
		
	/*********************************************************************/
	
                
	}


	public String ValidarDatos() {//Metodo para comprobar que los datos esten completos.
        String msj = "";
        if (this.PO.ObtenerDatos().getNombreObra().equals("")) {//Si TxtNombreMat esta vacio.
            msj += "Por favor ingrese el nombre de la obra. \n";
        }
        if (this.PO.ObtenerDatos().getEncargadoObra().equals("")) {//Si TxtCodigoMat esta vacio.
            msj += "Por favor ingrese un encargado. \n";
        }
        if (this.PO.ObtenerDatos().getTelefonoObra().equals("")) {//Si TxtSelectCant esta vacio.
            msj += "Por favor ingrese un telefono de contacto. \n";
        }
        if (this.PO.ObtenerDatos().getDireccionObra().equals("")) {//Si TxtSelectCant esta vacio.
            msj += "Por favor ingrese la direccion de la obra. \n";
        }
        
        return msj;//devuelve msj.
    }
	
	
	public void getObras() {
		
		System.out.println("HOLA");
		
		List<ObrasDTO> obras = this.miObraDAO.ConsultarTodos();
    	
    	PO.limpiarTabla();
        
        for (int i = 0; i < obras.size(); i++) {
            System.out.println(obras.get(i).getNombreObra()+"Estoy en Obras");
            this.PO.insertarTabla(obras.get(i).toArray());
        }
	}
	

	
	private String getNombre(String nombre) {
	
		return nombre; 	
	}
	
	public void setObra() {
		
		try {
			
			if (ValidarDatos().equals("")) {
				/*
				 *  Si el medoto ValidarDatos devuelve "", es decir, 
				 *  nada es porque los dos campos estan llenos. 
				 *  
				 */
				
				try {
					
					miObraDAO.Actualizar(this.PO.ObtenerDatos(),this.getNombre(nombre));
					PO.limpiar();
					
					this.getObras();
					
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
	
	public void registrarObra() {
		
		String btn = PO.getBtnAgregar().getText();
		
		try {
				
				if (ValidarDatos().equals("")) {
					/*
					 *  Si el medoto ValidarDatos devuelve "", es decir, 
					 *  nada es porque los dos campos estan llenos. 
					 *  
					 */
					
					try {
						
						this.miObraDAO.Ingresar(this.PO.ObtenerDatos());
						PO.limpiar();
						
						this.getObras();
						
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

}
