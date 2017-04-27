/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obyhat.controlador;

import com.obyhat.modelo.dao.AsignarDAO;
import com.obyhat.modelo.dao.MaterialesDAO;
import com.obyhat.modelo.dao.ObraDAO;
import com.obyhat.modelo.dto.AsignarDTO;
import com.obyhat.modelo.dto.CategoriaDTO;
import com.obyhat.modelo.dto.MaterialesDTO;
import com.obyhat.modelo.dto.ObrasDTO;
import com.obyhat.modelo.dto.UsuarioDTO;
import com.obyhat.vista.paneles.PanelAsignar;
import com.obyhat.vista.paneles.SeleccionarObra;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author joselara
 */
public class BotonesAsignar implements ActionListener {

	private PanelAsignar  PA;
	private AsignarDAO    asignarDAO;
	private MaterialesDAO misMateriales;
	private ObraDAO       miObra;
	private String        nombre;
	
	
	public BotonesAsignar(PanelAsignar PA) {
		
		this.PA = PA;
		this.asignarDAO = new AsignarDAO();
		this.miObra = new ObraDAO();
		this.misMateriales = new MaterialesDAO();
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.PA.getBtnAgregar()) {
			
        	System.out.println("Boton Agregar Escuchando");
        	
        	this.agregarMaterial();
        	
		}

		if (e.getSource() == this.PA.getBtnCancelar()) {
			
			System.out.println("Boton Cancelar Escuchando");
            
			//this.PA.limpiar();
			/*this.obtenerCategorias();
			this.PA.getBtnAgregar().setEnabled(true);
			this.PA.getBtnActualizar().setEnabled(false);*/
		}

		if (e.getSource() == this.PA.getBtnProcesarSelect()) {
			
			System.out.println("Boton 'Procesar' escuchando Rock!");
			
			this.procesarAsignacion();
		}
		
    }
    
    public void agregarMaterial() {
		
		int filaSeleccionada = PA.getTablaMateriales().getSelectedRow();
		String cantidadSele     = this.PA.getTxtCantidadSel().getText();
		int cantidadSelec;
		//String datos         = PA.getTablaMateriales().getValueAt(filaSeleccionada, 0).toString();
		
		if (cantidadSele.equals("")) {
			
			JOptionPane.showMessageDialog(null, "El campo cantidad no puede estar vacio");
		}
		
		else {
			
			cantidadSelec = Integer.parseInt(this.PA.getTxtCantidadSel().getText());
			
			try {
				
				if(filaSeleccionada >= 0 && cantidadSelec >= 1){

		            try {
						
		            	JOptionPane.showMessageDialog(null, "Ha seleccionado\n Y una cantidad de "+cantidadSelec,
								"Informacion", JOptionPane.INFORMATION_MESSAGE);
		            	
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(null, "No se pudo agregar el material seleccionado",
															"Informacion", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				else{
					
		            JOptionPane.showMessageDialog(null, "Debe seleccionar un material y una cantidad mayor a cero");
		        }
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e);
			}
		}
		
		
		
		
	}
    
    public void agregarAsignacion() {
		
		try {
				
				if (ValidarDatos().equals("")) {
					/*
					 *  Si el medoto ValidarDatos devuelve "", es decir, 
					 *  nada es porque los dos campos estan llenos. 
					 *  
					 */
					
					try {
						
						 /*
						  * Creando un arreglo de 3 posiciones que contendra los
						  * datos obtenidos en el formulario.
						  */
						
						Object [] datos = new Object[4];
						datos[0]= PA.ObtenerDatos().getObra();
						datos[1]= PA.ObtenerDatos().getFechaAsignacion();
						datos[2]= PA.ObtenerDatos().getNombreMaterial();
						datos[3]= PA.ObtenerDatos().getCantidadSeleccionada();
						
						//PA.llenarTabla(datos);
						
					} catch (Exception e2) {
						
						System.out.println("Error al insertar "+e2);
					}
				} else {
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
	
	public void eliminarSeleccion() {
		
		int filaSelecionada = PA.getTablaSeleccionados().getSelectedRow();
		System.out.println(filaSelecionada);
		
		if(filaSelecionada != -1){

            try {
				
            	PA.removerSeleccion(filaSelecionada);
			} catch (Exception e2) {
				
				JOptionPane.showMessageDialog(null, "La cedula no existe", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else{
			
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }
	}
	
	public void procesarAsignacion() {
		
		System.out.println("Entrando en asignacion");
		
		int canntidad = PA.getTablaSeleccionados().getRowCount();
		
		String fecha = null;
		int cantidadSel = 0;
		
		try {
			
			for (int i = 0; i < canntidad; i++) {
				
				fecha 	= PA.getTablaSeleccionados().getValueAt(i, 1).toString();
				cantidadSel = Integer.parseInt(PA.getTablaSeleccionados().getValueAt(i, 3).toString());
				
				asignarDAO.Ingresar(new AsignarDTO(fecha, cantidadSel));
			}
			
			JOptionPane.showMessageDialog(null, "Procesado exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error \n"+e);
		}
		
		
	}
	
	public void obtenerObras() {
		
		List<ObrasDTO> obras = this.miObra.ConsultarTodos();

		this.PA.vaciar_comboObra();
		
		for (int i = 0; i < obras.size(); i++) {
            System.out.println(obras.get(i).getNombreObra()+"Estoy en Asignar");
            PA.llenarComboObra(obras.get(i).getNombreObra());
        }
	}
	
	public void obtenerMateriales() {
		
		List<MaterialesDTO> materiales = this.misMateriales.ConsultarTodos();
    	
		//SO.limpiarTabla();
        
        for (int i = 0; i < materiales.size(); i++) {
            
        	System.out.println(materiales.get(i).getNombreMaterial()+" - "+materiales.get(i).getCantidadMaterial());
            this.PA.llenarTablaMat(materiales.get(i).toArrayDos());
        }
	}
    
    public String ValidarDatos() {//Metodo para comprobar que los datos esten completos.
        
    	String msj = "";
        
        //@Deprecated
        if (this.PA.ObtenerDatos().getObra().equals("")) {//Si TxtNombreMat esta vacio.
            msj += "Por favor ingrese el nombre de la obra. \n";
        }
        if (this.PA.ObtenerDatos().getFechaAsignacion().equals("")) {//Si TxtCodigoMat esta vacio.
            msj += "Por favor ingrese un encargado. \n";
        }
        if (this.PA.ObtenerDatos().getNombreMaterial().equals("")) {//Si TxtSelectCant esta vacio.
            msj += "Por favor ingrese un telefono de contacto. \n";
        }
        if (this.PA.ObtenerDatos().getCantidadSeleccionada() == 0) {//Si TxtSelectCant esta vacio.
            msj += "Por favor ingrese la direccion de la obra. \n";
        }
        
        return msj;//devuelve msj.
    }
	
}
