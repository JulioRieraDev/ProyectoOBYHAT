package com.obyhat.controlador;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.obyhat.modelo.dao.MaterialesDAO;
import com.obyhat.vista.paneles.PanelMateriales;
 
public class BotonesMaterial implements ActionListener {
   
    private PanelMateriales PM;
    private MaterialesDAO materialesDAO;
    
    
   
    public BotonesMaterial(PanelMateriales PM) {
       
        this.PM = PM;
        this.materialesDAO = new MaterialesDAO();
        
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == PM.getBtnAgregar()) {
           
            System.out.println("Boton Guardar escuchando a Skrillex");
           
            
            try {
    			
    			if (ValidarDatos().equals("")) {
    				/*
    				 *  Si el medoto ValidarDatos devuelve "", es decir, 
    				 *  nada es porque los dos campos estan llenos. 
    				 *  
    				 */
    				
    				try {
    					
    					materialesDAO.Ingresar(PM.ObtenerDatos());
    					
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
        
         
        if (e.getSource() == this.PM.getBtnEliminar()) {
			
        	
		} 
        
         
         
                
    }
    
    public String ValidarDatos() {//Metodo para comprobar que los datos esten completos.
        
		String msj = "";
		
		if (this.PM.getComboCategoria().getSelectedIndex() == 0) {
			msj += "Por favor ingrese la categoria. \n";
		}
		
        if (this.PM.getTxtNombreMat().getText().equals("")) {//Si TxtNombreMat esta vacio.
            msj += "Por favor ingrese el nombre de la categoria. \n";
        }
        
        if (this.PM.getTxtCantidadMat().getText().equals("")) {//Si TxtNombreMat esta vacio.
            msj += "Por favor ingrese cantidad. \n";
        }
        
        if (this.PM.obtenerFecha().equals("")) {//Si TxtNombreMat esta vacio.
            msj += "Por favor ingrese fecha. \n";
        }
        
        return msj;//devuelve msj.
    }
    
}