/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obyhat.vista.paneles;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.obyhat.resources.fonts.fontComun;

/**
 *
 * @author Jeis
 */
public class PanelHistorialObras extends javax.swing.JPanel {

    DefaultTableModel modeloTabla;
    String [][] datos = {};
    String [] column = {"Fecha","Nombre","Cantidad Seleccionada"};
    
    public static final Color btnColor = new Color(108, 122, 137);
    
    public PanelHistorialObras() {
        initComponents();
        Creandotabla();
        //Control();
        
        
    }
    
    public void Creandotabla(){
    
        modeloTabla = new DefaultTableModel(datos,column);
        tablaSeleccionados.setModel(modeloTabla);
        
    }
    
   /* public void Control(){
    
        ControladorAsignar CA = new ControladorAsignar(this);
        btnBuscar.addActionListener(CA);
        btnAgregar.addActionListener(CA);
        btnEditarSelect.addActionListener(CA);
        btnEliminarSelect.addActionListener(CA);
        btnProcesarSelect.addActionListener(CA);
    }*/
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        panelDerecho = new javax.swing.JPanel();
        scrollTabla2 = new javax.swing.JScrollPane();
        tablaSeleccionados = new javax.swing.JTable();
        labelTitulo2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        setLayout(new java.awt.GridLayout(1, 0));

        panelDerecho.setBackground(new java.awt.Color(46, 64, 83));
        panelDerecho.setLayout(new java.awt.GridBagLayout());

        tablaSeleccionados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaSeleccionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaSeleccionados.setRowHeight(19);
        scrollTabla2.setViewportView(tablaSeleccionados);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 811;
        gridBagConstraints.ipady = 226;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(55, 57, 186, 59);
        panelDerecho.add(scrollTabla2, gridBagConstraints);

        labelTitulo2.setFont(fontComun.fontTit);
        labelTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo2.setText("Historial de materiales agregados");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(57, 57, 0, 0);
        panelDerecho.add(labelTitulo2, gridBagConstraints);

        add(panelDerecho);
    }// </editor-fold>//GEN-END:initComponents

   

    public JTable getTablaSeleccionados() {
        return tablaSeleccionados;
    }

    public void setTablaSeleccionados(JTable tablaSeleccionados) {
        this.tablaSeleccionados = tablaSeleccionados;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }


    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitulo2;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JScrollPane scrollTabla2;
    private javax.swing.JTable tablaSeleccionados;
    // End of variables declaration//GEN-END:variables
}
