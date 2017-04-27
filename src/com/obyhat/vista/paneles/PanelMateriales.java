/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obyhat.vista.paneles;

import com.obyhat.controlador.BotonesMaterial;
import com.obyhat.resources.components.txt;
import com.obyhat.resources.components.labelForm;
import com.obyhat.resources.components.Botones;
import com.obyhat.resources.components.labelTitulo;

import com.obyhat.controlador.TextoMateriales;
import com.obyhat.modelo.dto.MaterialesDTO;
import com.obyhat.resources.components.Separator;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.freixas.jcalendar.JCalendarCombo;

/**
 *
 * @author Jafeht
 */
public class PanelMateriales extends JPanel{
    
    private DefaultTableModel modeloTabla;
    private String [][] datos = {};
    private String [] column = {"Categoria","Nombre","Cantidad","Fecha Ingreso"};
    private JTable TablaMaterialesReg;
    
    private JPanel panelCentral,panelIzquierdo;
    private JScrollPane scrollPane,scrollPane2;
    
    private labelForm lcategoriaMaterial,lnombreMaterial,lcantidadMaterial,lfechaIngreso;
    private txt txtBuscarMat,txtCantidadMat,txtCodigoMat,txtFechaIngreso,txtNombreMat;
    private Separator separatorNom,separatorEnc,separatorDir,separatorTel;
    private labelTitulo labelTitulo1,labelTitulo2;
    private Botones btnAgregar,btnModificar,btnEliminar,btnBuscarMat;
    
     private javax.swing.JComboBox<String> comboCategoria;    
     private JDateChooser  comboFecha;
     
    
    private void dimension(int x, int y, JPanel p){
    
        Dimension panelD= new Dimension(x,y);
        p.setPreferredSize(panelD);
        p.setMaximumSize(panelD);
    }
    
    public JScrollPane Creandotabla(JScrollPane scrollPane){
    
        modeloTabla = new DefaultTableModel(datos,column);
        
        scrollPane= new JScrollPane();
        TablaMaterialesReg= new JTable();
        TablaMaterialesReg.getTableHeader().setFont(new Font ("Acme", 1,16));// Encabezado de tablaObras
        //tablaObras.getTableHeader().setBackground(Color.black);
        // tablaObras.getTableHeader().setForeground(Color.BLUE);
        //tablaObras.getTableHeader().setReorderingAllowed(false);// No permite que se muevan las columnas
        TablaMaterialesReg.setModel(modeloTabla);
        scrollPane.setViewportView(TablaMaterialesReg);
        
        return scrollPane;
    }
    
    
    public void LimpiarCampos(){
        
        txtNombreMat.setText("");
        txtCodigoMat.setText("");
        txtCantidadMat.setText("");
        
        
    }
    
    public PanelMateriales(){
        setLayout(new GridLayout(1, 0, 0, 0));
		
	scrollPane = new JScrollPane();	
		
	JPanel panelContenedor = new JPanel();
	panelContenedor.setBackground(Color.GRAY);
        panelContenedor.setPreferredSize(new Dimension(950, 600));
	panelContenedor.setLayout(new BorderLayout(0, 0));
		
        PanelIzquierdo();
        PanelCentral();
        
        panelContenedor.add(panelIzquierdo,BorderLayout.WEST);
        panelContenedor.add(panelCentral,BorderLayout.CENTER);
        
        scrollPane.setViewportView(panelContenedor);
        add(scrollPane);
        
        Control();
     }      
    

    public void Control(){
    
       //ControladorObra CO = new ControladorObra(this);
       //TextoMateriales CM = new TextoMateriales(this);
        
      // btnAgregar.addActionListener(CM);
        
        //btnModificar.addActionListener(CM);
        //btnEliminar.addActionListener(CM);
        
        BotonesMaterial BM = new BotonesMaterial(this);
        btnAgregar.addActionListener(BM);
        btnEliminar.addActionListener(BM);
         
        //btnBuscarMat.addActionListener(CM);
        
        //txtNombreMat.addKeyListener(CM);
        //txtCantidadMat.addKeyListener(CM);
        
     
    }
    
    private void PanelIzquierdo(){
    
        /*---------------------------------------------------------------------*/
        /*-------------------- Contenedor Panel Izquierdo ---------------------*/
        
        // CONSTANTES
            // Separacion del Borde Izquierdo.
            int SBI = 25;
            
        panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(new Color(46,64,83));
        panelIzquierdo.setLayout(null);
        panelIzquierdo.setBorder(new LineBorder(new Color(42, 59, 80),3,true));
        
                labelTitulo1 = new labelTitulo("Registrar Materiales");
		labelTitulo1.setBounds(23, 20, 245, 25);
		panelIzquierdo.add(labelTitulo1);
                
                
                lcategoriaMaterial = new labelForm("Categoria"); 
                lcategoriaMaterial.setBounds(24, 60, 212, 25);
                panelIzquierdo.add(lcategoriaMaterial);
                comboCategoria = new JComboBox();
                comboCategoria.setBounds(25, 90, 291, 28);
                panelIzquierdo.add(comboCategoria);       
                comboCategoria.setModel(new DefaultComboBoxModel(new String[]{"Selecciones una Opcion","Arena","Cemento"}));
                separatorNom = new Separator();
		separatorNom.setBounds(25, 130, 290, 14);
		panelIzquierdo.add(separatorNom);
		
		lnombreMaterial = new labelForm("Nombre del material");
		lnombreMaterial.setBounds(24, 140, 212, 25);
		panelIzquierdo.add(lnombreMaterial);
		txtNombreMat = new txt();
		txtNombreMat.setBounds(25, 170, 291, 28);
		panelIzquierdo.add(txtNombreMat);
		separatorNom = new Separator();
		separatorNom.setBounds(25, 205, 290, 14);
		panelIzquierdo.add(separatorNom);
		
				
		lcantidadMaterial = new labelForm("Cantidad material");
		lcantidadMaterial.setBounds(24, 206, 212, 25);
		panelIzquierdo.add(lcantidadMaterial);
		txtCantidadMat = new txt();
                txtCantidadMat.setBounds(25, 235, 291, 28);
		panelIzquierdo.add(txtCantidadMat);
		separatorDir = new Separator();
		separatorDir.setBounds(25, 270, 290, 14);
		panelIzquierdo.add(separatorDir);
		
		lfechaIngreso = new labelForm("Fecha de ingreso");
	        lfechaIngreso.setBounds(24, 300, 135, 25);
		panelIzquierdo.add(lfechaIngreso);
		comboFecha =  new JDateChooser();
                comboFecha.setBounds(25, 326, 291, 28);
		panelIzquierdo.add(comboFecha);
		separatorTel = new Separator();
		separatorTel.setBounds(25, 361, 290, 14);
		panelIzquierdo.add(separatorTel);
		
		
                
                btnAgregar = new Botones("Agregar");
		btnAgregar.setBounds(25, 511, 90, 35);
		panelIzquierdo.add(btnAgregar);
		
		btnModificar = new Botones("Modificar");
                btnModificar.setEnabled(false);
		btnModificar.setBounds(126, 511, 90, 35);
		panelIzquierdo.add(btnModificar);
		
		btnEliminar = new Botones("Eliminar");
                //btnEliminar.setEnabled(false);
		btnEliminar.setBounds(226, 511, 90, 35);
		panelIzquierdo.add(btnEliminar);
		
        
        dimension(350,0,panelIzquierdo);
        
        /*---------------------------------------------------------------------*/
        /*-------------------- Contenedor Panel Izquierdo ---------------------*/
    }


    private void PanelCentral(){
    
        /*---------------------------------------------------------------------*/
        /*--------------------- Contenedor Panel Central ----------------------*/
        
        // CONSTANTES
            //Ancho del Panel Este y Panel Oeste.
            int PEO = 15;
            
            //Alto del Panel Norte y Panel Sur.
            int PNS = 140;
            
        panelCentral = new JPanel();
        //panelCentral.setBackground(new Color(52, 73, 94));
        panelCentral.setLayout(new BorderLayout());
        
        JPanel PN = new JPanel();
        PN.setBackground(new Color(52, 73, 94));
        dimension(0,PNS,PN);
            labelTitulo2 = new labelTitulo("Buscar material");
            labelTitulo2.setBounds(23, 47, 245, 25);
            panelCentral.add(labelTitulo2);
        panelCentral.add(PN,BorderLayout.NORTH);
        
        
        JPanel PO = new JPanel();
        PO.setBackground(new Color(52, 73, 94));
        dimension(PEO,0,PO);
        panelCentral.add(PO,BorderLayout.WEST);
        
        
        PanelInicio PC = new PanelInicio();
        PC.setLayout(new BorderLayout());
        
            PC.add(Creandotabla(scrollPane2));
            
        panelCentral.add(PC);
        
        
        JPanel PE = new JPanel();
        PE.setBackground(new Color(52, 73, 94));
        dimension(PEO,0,PE);
        panelCentral.add(PE,BorderLayout.EAST);
        
        
        JPanel PS = new JPanel();
        PS.setBackground(new Color(52, 73, 94));
        dimension(0,PNS,PS);
        panelCentral.add(PS,BorderLayout.SOUTH);
    
        
        
    }
    
    public MaterialesDTO ObtenerDatos() {
		
    	return new MaterialesDTO(
    			this.txtNombreMat.getText() ,                     
    			Integer.parseInt(this.txtCantidadMat.getText()),
    			this.obtenerFecha());      			
	}
    
    public String obtenerFecha() {
		
    	String fecha = "";
    	
    	try {
			
    		int year  = this.comboFecha.getCalendar().get(Calendar.YEAR);
        	int month = this.comboFecha.getCalendar().get(Calendar.MONTH)+1;
        	int day   = this.comboFecha.getCalendar().get(Calendar.DAY_OF_MONTH);
        	
        	fecha = year+"-"+month+"-"+day;
        	
        	return fecha;
        	
		} catch (Exception e) {
			
			return fecha;
		}
	}

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public JTable getTablaMaterialesReg() {
        return TablaMaterialesReg;
    }

    public void setTablaMaterialesReg(JTable TablaMaterialesReg) {
        this.TablaMaterialesReg = TablaMaterialesReg;
    }

    public labelForm getLcategoriaMaterial() {
        return lcategoriaMaterial;
    }

    public void setLcategoriaMaterial(labelForm lcategoriaMaterial) {
        this.lcategoriaMaterial = lcategoriaMaterial;
    }

    public labelForm getLcantidadMaterial() {
        return lcantidadMaterial;
    }

    public void setLcantidadMaterial(labelForm lcantidadMaterial) {
        this.lcantidadMaterial = lcantidadMaterial;
    }

    public labelForm getLfechaIngreso() {
        return lfechaIngreso;
    }

    public void setLfechaIngreso(labelForm lfechaIngreso) {
        this.lfechaIngreso = lfechaIngreso;
    }

    public txt getTxtBuscarMat() {
        return txtBuscarMat;
    }

    public void setTxtBuscarMat(txt txtBuscarMat) {
        this.txtBuscarMat = txtBuscarMat;
    }

    public txt getTxtCantidadMat() {
        return txtCantidadMat;
    }

    public void setTxtCantidadMat(txt txtCantidadMat) {
        this.txtCantidadMat = txtCantidadMat;
    }

    public txt getTxtCodigoMat() {
        return txtCodigoMat;
    }

    public void setTxtCodigoMat(txt txtCodigoMat) {
        this.txtCodigoMat = txtCodigoMat;
    }

    public txt getTxtFechaIngreso() {
        return txtFechaIngreso;
    }

    public void setTxtFechaIngreso(txt txtFechaIngreso) {
        this.txtFechaIngreso = txtFechaIngreso;
    }

    public txt getTxtNombreMat() {
        return txtNombreMat;
    }

    public void setTxtNombreMat(txt txtNombreMat) {
        this.txtNombreMat = txtNombreMat;
    }

    public Botones getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(Botones btnAgregar) {
        this.btnAgregar = btnAgregar;
    }

    public Botones getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(Botones btnModificar) {
        this.btnModificar = btnModificar;
    }

    public Botones getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(Botones btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public Botones getBtnBuscarMat() {
        return btnBuscarMat;
    }

    public void setBtnBuscarMat(Botones btnBuscarMat) {
        this.btnBuscarMat = btnBuscarMat;
    }

    public JComboBox<String> getComboCategoria() {
        return comboCategoria;
    }

    public void setComboCategoria(JComboBox<String> comboCategoria) {
        this.comboCategoria = comboCategoria;
    }

    public JDateChooser getComboFecha() {
        return comboFecha;
    }

    public void setComboFecha(JDateChooser comboFecha) {
        this.comboFecha = comboFecha;
    }
    
    
    

    
    

   
    
      public static void main(String args[]) {
		JFrame frame = new JFrame("Probando panel individual...");
		frame.setLayout(new GridLayout());
		PanelMateriales PA = new PanelMateriales();
		frame.add(PA);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setSize(1300, 650);
                frame.setLocation(100, 250);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}      
}
