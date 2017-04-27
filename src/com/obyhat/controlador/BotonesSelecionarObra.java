package com.obyhat.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.obyhat.modelo.dao.MaterialesDAO;
import com.obyhat.modelo.dto.CategoriaDTO;
import com.obyhat.modelo.dto.MaterialesDTO;
import com.obyhat.vista.paneles.PanelAsignar;
import com.obyhat.vista.paneles.SeleccionarObra;


public class BotonesSelecionarObra implements ActionListener {

	private SeleccionarObra SO;
	private BotonesAsignar	BA;
	private MaterialesDAO   materiales;
	private String 			nombre;
	
	
	public BotonesSelecionarObra(SeleccionarObra SO) {
		
		this.BA = BA;
		this.SO = SO;
		this.materiales = new MaterialesDAO();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.SO.getBtnSeleccionar()) {
			
			System.out.println("Boton Seleccionar escuchando");
			
			seleccionarMaterial();
		}
	}
	
	public void obtenerMateriales() {
		
		List<MaterialesDTO> materialesDTO = this.materiales.ConsultarTodos();
    	
		//SO.limpiarTabla();
        
        for (int i = 0; i < materialesDTO.size(); i++) {
            
        	System.out.println(materialesDTO.get(i).getNombreMaterial()+" - "+materialesDTO.get(i).getCantidadMaterial());
            this.SO.insertarTabla(materialesDTO.get(i).toArrayDos());
        }
	}
	
	public void seleccionarMaterial() {
		
		//Obtenet la Fila seleccionada.
		int filaSeleccionada = SO.getTablaObras().getSelectedRow();
        
		if (filaSeleccionada >= 0) {

			try {
					
				String [] datos = new String[2];
				datos[0]= SO.getTablaObras().getValueAt(filaSeleccionada, 0).toString();
				datos[1]= SO.getTablaObras().getValueAt(filaSeleccionada, 1).toString();
				
				System.out.println(datos[0]+" - "+datos[1]);
				
				
            	//PA.llenarFormulario(datos);
            	//PA.removerSeleccion(filaSeleccionada);
                	
                	
			} catch (Exception e2) {
					
				JOptionPane.showMessageDialog(null, "La cedula no existe", "Informacion \n"+e2, JOptionPane.INFORMATION_MESSAGE);
				System.out.println(e2);
			}
		}
		
		else{
            
			JOptionPane.showMessageDialog(null, "Seleccione la fila a eliminar");
		}
	}
}
