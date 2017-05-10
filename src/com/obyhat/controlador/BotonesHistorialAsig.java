package com.obyhat.controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.obyhat.modelo.dao.AsignarDAO;
import com.obyhat.modelo.dao.MaterialesDAO;
import com.obyhat.modelo.dto.AsignarDTO;
import com.obyhat.modelo.dto.CategoriaDTO;
import com.obyhat.modelo.dto.MaterialesDTO;
import com.obyhat.modelo.dto.ObrasDTO;
import com.obyhat.vista.paneles.HistorialAsignaciones;
import com.obyhat.vista.paneles.PanelAsignar;


public class BotonesHistorialAsig implements ActionListener {

	private HistorialAsignaciones HA;
	private MaterialesDAO   materiales;
	private AsignarDAO    	asignarDAO;
	
	
	public BotonesHistorialAsig(HistorialAsignaciones HA) {
		
		this.HA = HA;
		this.materiales = new MaterialesDAO();
		this.asignarDAO = new AsignarDAO();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.HA.getBtnMostrar()) {
			
			System.out.println("Boton mostrar escuchando");
			
			this.HA.limpiarTabla();
			this.obtenerMaterialesAsig();
		}
		
		if (e.getSource() == this.HA.getBtnAtras()) {
			
			System.out.println("Boton atras escuchando");
			
			HA.getPanelTablaTotalMat().setVisible(false);
			HA.getPanelTablaAsig().setVisible(true);
		}
	}
	
	public void obtenerMaterialesAsig() {
		
		int filaSelecionada = HA.getTablaTotalAsig().getSelectedRow();
		System.out.println(filaSelecionada);
		
		if(filaSelecionada != -1){

            try {

            	HA.getPanelTablaTotalMat().setVisible(true);
    			HA.getPanelTablaAsig().setVisible(false);
    			
    			int idAsignacion  = Integer.parseInt(HA.getTablaTotalAsig().getValueAt(filaSelecionada, 0).toString());
    			
    			List<AsignarDTO> materialesAsig = this.asignarDAO.materialesAsignados(idAsignacion);
    			
    			this.HA.getLnombreObra().setText(materialesAsig.get(1).getObra()+"     ");
    			this.HA.getLfechaAsignacion().setText("     "+materialesAsig.get(1).getFechaAsignacion());
    			
    			for (int i = 0; i < materialesAsig.size(); i++) {
    	             
    	            this.HA.insertarTablaMat(new Object[] {materialesAsig.get(i).getNombreMaterial(),
    								                       materialesAsig.get(i).getCantidadSeleccionada()});
    	        }
            	
			} catch (Exception e2) {
				
				JOptionPane.showMessageDialog(null, "Error al obtener los datos de los materiales asignados", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else{
			
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        }
	}
	
	public void obtenerAsignaciones() {
		
		try {
      
			List<AsignarDTO> asignaciones = this.asignarDAO.ConsultarTodos();
			
			for (int i = 0; i < asignaciones.size(); i++) {
	            
	            this.HA.insertarTabla(new Object[] {asignaciones.get(i).getIdAsignacion(),
								                    asignaciones.get(i).getFechaAsignacion(),
								                    asignaciones.get(i).getObra(),
								                    asignaciones.get(i).getEncargadoObra(),
								                    asignaciones.get(i).getSumaMaterialesAsig()});
	        }
	    } catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, "Error al obtener las asignaciones \n"+e);
	    }
	}
}
