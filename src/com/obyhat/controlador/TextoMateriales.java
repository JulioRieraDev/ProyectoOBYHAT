/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obyhat.controlador;

import java.awt.event.ActionEvent;

import  com.obyhat.vista.paneles.PanelMateriales;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Jafeht
 */
public class TextoMateriales implements KeyListener,ActionListener {
    PanelMateriales PMateriales;
    
    public TextoMateriales (PanelMateriales PMateriales){
    this.PMateriales=PMateriales;
    
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==PMateriales.getBtnAgregar()){
         System.out.println("Boton Escuchando");
         try{
         if (ValidarDatos().equals("")){
         
             Object [] datos = new Object[2];
                        datos[0]= PMateriales.getTxtNombreMat().getText();
                        datos[1]= PMateriales.getTxtCantidadMat().getText();
                        
                        
                        PMateriales.getModeloTabla().addRow(datos);
                        PMateriales.LimpiarCampos();
                        
                  JOptionPane.showMessageDialog(null, "Los dos datos han sido ingresados :-)", "Validando Datos",
                  JOptionPane.INFORMATION_MESSAGE);
                        
                        PMateriales.getBtnModificar().setEnabled(true);
                        PMateriales.getBtnEliminar().setEnabled(true);         
         }
         else{//de lo contrario emite el mensaje
            
                        JOptionPane.showMessageDialog(null, "ERROR!! \n" + ValidarDatos(), "Validando Datos",
                            JOptionPane.ERROR_MESSAGE);
                        
                    
                    }
             
         }
         catch(Exception e1){
            
                    System.out.println("Error en Eventos del Boton Agregar");
                }
         }
         
          if (e.getSource()==PMateriales.getBtnModificar()) {
            
            System.out.println("Boton 'Modificar' escuchando Rock!");
            
            //Obtenet la Fila seleccionada.
            int filaSeleccionada = PMateriales.getTablaMaterialesReg().getSelectedRow();
                if (filaSeleccionada >= 0) {

                    PMateriales.getTxtNombreMat().setText(PMateriales.getTablaMaterialesReg().getValueAt(filaSeleccionada, 0).toString());
                    PMateriales.getTxtCantidadMat().setText(PMateriales.getTablaMaterialesReg().getValueAt(filaSeleccionada, 1).toString());
                    
                    //Remover Fila una vez seleccionada para modificar.
                    PMateriales.getModeloTabla().removeRow(filaSeleccionada);
                   
                    PMateriales.getBtnModificar().setEnabled(false);
                    PMateriales.getBtnEliminar().setEnabled(false);
                }
                else{
                
                    JOptionPane.showMessageDialog(PMateriales, "Seleccione una fila");
                }
        }
          
          if (e.getSource()==PMateriales.getBtnEliminar()) {
            
            System.out.println("A sido presionado el boton Eliminar");
            
            int filaSeleccionada = PMateriales.getTablaMaterialesReg().getSelectedRow();
                if (filaSeleccionada >= 0) {

                    int i = JOptionPane.showConfirmDialog(null,"Desea eliminar "
                            + "la fila seleccionada?","Titulo",JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);            
                
                    if (i==0) {
                    
                        PMateriales.getModeloTabla().removeRow(filaSeleccionada);
                    }
                }
                else{
                
                    JOptionPane.showMessageDialog(PMateriales, "Seleccione una fila");
                }
        }
    
    }
    
    public String ValidarDatos() {//Metodo para comprobar que los datos esten completos.
        String msj = "";
        if (PMateriales.getTxtNombreMat().getText().equals("")) {//Si TxtNombreMat esta vacio.
            msj += "Por favor ingrese el nombre de la obra. \n";
        }
        if (PMateriales.getTxtCantidadMat().getText().equals("")) {//Si TxtCodigoMat esta vacio.
            msj += "Por favor ingrese el nombre del encargado. \n";
        }
        return msj;//devuelve msj.
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
         if (e.getSource()==PMateriales.getTxtNombreMat()) {
            
            int k = (int) e.getKeyChar();//k = al valor de la tecla presionada

            if (k > 47 && k < 58) {//Si el caracter ingresado es una letra
                e.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
                JOptionPane.showMessageDialog(null, "NUMEROS NO PERMITIDOS", "Validando Datos",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (k == 10) {//si se presiona enter
                //transfiere el foco
                PMateriales.getTxtNombreMat().transferFocus();
            }

            //Limitar el numero de caracteres. Solo se pueden ingresar 20 caracteres
            if (PMateriales.getTxtNombreMat().getText().length() >= 30) {
                e.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
                JOptionPane.showMessageDialog(null, "Ha excedido el numero maximo de caracteres!!! (30)", "Validando Datos",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
         
         if (e.getSource()==PMateriales.getTxtCantidadMat()) {
            
            int k = (int) e.getKeyChar();//k = al valor de la tecla presionada
            
            if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {//Si el caracter ingresado es una letra
            e.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Validando Datos",
                    JOptionPane.ERROR_MESSAGE);
            }

            if (k == 241 || k == 209) {//Si el caracter ingresado es una letra
                e.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
                JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Validando Datos",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (k == 10) {//si se presiona enter
                //transfiere el foco
                PMateriales.getBtnAgregar().transferFocus();
            }

            //Limitar el numero de caracteres. Solo se pueden ingresar 5 caracteres
            if (PMateriales.getTxtCantidadMat().getText().length() >= 2) {
                e.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
                JOptionPane.showMessageDialog(null, "Ha excedido la cantidad"
                        + " de Stock disponible!!!", "Validando Datos",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
