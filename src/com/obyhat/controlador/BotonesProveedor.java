package com.obyhat.controlador;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.obyhat.modelo.dao.MaterialesDAO;
import com.obyhat.modelo.dao.ProveedorDAO;
import com.obyhat.modelo.dto.MaterialesDTO;
import com.obyhat.modelo.dto.ProveedorDTO;
import com.obyhat.vista.paneles.PanelMateriales;
import com.obyhat.vista.paneles.PanelProveedores;
import java.util.List;
 
public class BotonesProveedor implements ActionListener {
   
    private PanelProveedores PP;
    private ProveedorDAO proveedorDAO;
    
    
   
    public BotonesProveedor(PanelProveedores PP) {
       
        this.PP = PP;
        this.proveedorDAO = new ProveedorDAO();
        
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == PP.getBtnAgregar()) {
           
            System.out.println("Boton Guardar escuchando a Skrillex");
           
          try {		
		if(ValidarDatos().equals("")){                                          
			try {		
			      this.proveedorDAO.Ingresar(this.PP.ObtenerDatos());
			      this.PP.LimpiarCampos();
					} catch (Exception e2) {		
					  System.out.println("Error al insertar "+e2);
					}
				}				
				else{
					
	                    JOptionPane.showMessageDialog(null, "ERROR!! \n" + ValidarDatos(), "Validando Datos",JOptionPane.ERROR_MESSAGE);
	            }		
		} catch(Exception e1){
	           
	            JOptionPane.showMessageDialog(null, "Error en Eventos del Boton Agregar. \n" + e1, "Error",JOptionPane.ERROR_MESSAGE);
	      }
         
         this.ObtenerProveedor();
        }  
        
      
        
        if (e.getSource()==PP.getBtnBuscarMat()) {
            System.out.println("Escuchando boton actualizar");
            ObtenerProveedor();
        }
        
        if (e.getSource() == PP.getBtnEliminar()) {
	
			System.out.println("Boton 'Eliminar' escuchando Rock!");
			
			int filaSelecionada = PP.getTablaProvedorReg().getSelectedRow();
			
			if(filaSelecionada != -1){

                String nombreObra = PP.getTablaProvedorReg().getValueAt(filaSelecionada, 0).toString();
                

            	int opc = JOptionPane.showConfirmDialog(null, " Desea eliminar permanentemente la obra: "+nombreObra+"? ", "Confirmar eliminacion ",
            	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                /*
            	 * Le preguntare al usuario si en realidad desea modificar la categoria.
            	 */

                if (opc == 0) {
                
	                try {
						
	                	PP.getModeloTabla().removeRow(filaSelecionada);
	                	this.proveedorDAO.Eliminar(nombreObra);
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(null, "La cedula no existe", "Informacion", JOptionPane.INFORMATION_MESSAGE);
					}
                }    
			}
			else{
				
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            }
		}
        
    }
    
    
    public String ValidarDatos() {//Metodo para comprobar que los datos esten completos.
        
         String msj = "";
		
        if (this.PP.getTxtRif().getText().equals("")) {
            msj += "Por favor escriba un nombre. \n";
        }
        if(this.PP.getTxtNombreRazon().getText().equals("")){
         msj += "Por favor escriba una cantidad. \n";
        }
        if(this.PP.getTxtTelefono().getText().equals("")){
         msj += "Por favor escriba una cantidad. \n";
        }
        if(this.PP.getTxtDireccion().getText().equals("")){
         msj += "Por favor escriba una cantidad. \n";
        }
        if(this.PP.getTxtEmail().getText().equals("")){
         msj += "Por favor escriba una cantidad. \n";
        }
        
           
        return msj;//devuelve msj.
    }
    
    //logica con el llenado de la tabla
    public void ObtenerProveedor() {
        List<ProveedorDTO> proveedor = this.proveedorDAO.ConsultarTodos();
    	
    	PP.limpiarTabla();
        
        for (int i = 0; i < proveedor.size(); i++) {
            System.out.println(proveedor.get(i).getRif()+""+proveedor.get(i).getRazonSocial()+""+proveedor.get(i).getTelefono()+""+proveedor.get(i).getDireccion()+""+proveedor.get(i).getEmail());
            PP.llenarTabla(proveedor.get(i).toArray());
            //this.PM.insertarTabla(materiales.get(i).toArray());
        
        
    }
  }
}