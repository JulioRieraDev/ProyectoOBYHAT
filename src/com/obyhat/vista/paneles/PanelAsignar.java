/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obyhat.vista.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle.Control;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.freixas.jcalendar.JCalendarCombo;

import com.obyhat.controlador.BotonesAsignar;
import com.obyhat.controlador.TextoAsignar;
import com.obyhat.modelo.dto.AsignarDTO;
import com.obyhat.modelo.dto.CategoriaDTO;
import com.obyhat.resources.components.Botones;
import com.obyhat.resources.components.Separator;
import com.obyhat.resources.components.labelForm;
import com.obyhat.resources.components.labelTitulo;
import com.obyhat.resources.components.txt;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
/**
 *
 * @author Jeis
 */
public class PanelAsignar extends JPanel{
    
	// Tablas.
    private DefaultTableModel modeloTablaMat, modeloTablaAsig;
    private JTable 		tablaSeleccionados,tablaMateriales;
    private String [][] datosMat   = {};
    private String [][] datosAsig  = {};
    private String [] 	columnMat  = {"Material","Cantidad Disponible"};
    private String [] 	columnAsig = {"Obra","Fecha","Material","Cantidad Seleccionada"};
    
    //Contenedores.
    private JPanel 		panelCentral,panelIzquierdo,panelCentral2,panelNorte,panelSur,
    			   		panelConstructor,PO,PE;
    private JScrollPane scrollConstructor,scrollTablaMat,scrollTablaAsig;
    
    // Componentes personalizados.
    private labelForm 	selecCantidad;
    private txt 		txtNombreMat,txtCantidadSel;
    private Separator 	separatorNom,separatorCod;
    private labelTitulo labelTitulo1,materialesDis,asignarMat;
    private Botones 	btnAgregar,btnActualizar,btnCancelar,btnEliminarSelect,
    					btnProcesarSelect;
    
    private JComboBox 	comboObra;
    // Elegir fecha.
    private JDateChooser comboFecha;
    // Controladores
    private BotonesAsignar BA      = new BotonesAsignar(this);
    private TextoAsignar TA 	   = new TextoAsignar(this);
    private GridBagConstraints gbc = new GridBagConstraints();
    
    
    public PanelAsignar(){
        
    	setLayout(new GridLayout(1, 0, 0, 0));
		
    	scrollConstructor = new JScrollPane();	
		
    	panelConstructor = new JPanel();
    	panelConstructor.setBackground(Color.GRAY);
        panelConstructor.setLayout(new BorderLayout(0, 0));
        panelConstructor.setPreferredSize(new Dimension(950, 600));
        
        PanelIzquierdo();
        //PanelCentral();
        //panelSelObras();
        
        panelConstructor.add(panelIzquierdo,BorderLayout.WEST);
        panelConstructor.add(PanelCentral(),BorderLayout.CENTER);
        
        scrollConstructor.setViewportView(panelConstructor);
        add(scrollConstructor);
        
        // Llenar automaticamente las tablas con la consultas
        // a la base de datos.
        this.BA.obtenerObras();
        this.BA.obtenerMateriales();
        this.control();
     }      
    
    private void PanelIzquierdo(){
    
    	panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(new Color(46,64,83));
        panelIzquierdo.setPreferredSize(new Dimension(300,0));
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.setBorder(new LineBorder(new Color(42, 59, 80),3,true));
        
        labelTitulo1 = new labelTitulo("Registrar... ");
		labelTitulo1.setBounds(23, 47, 245, 25);
		panelIzquierdo.add(labelTitulo1);
        
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLocation(100, 23);
        panel.setLayout(new GridBagLayout());
        panelIzquierdo.add(panel);
        gbc.insets = new Insets(5,0,5,0);
            
        gbc.gridx = 0;	gbc.gridy = 0;
        JPanel P4 = new JPanel();
        P4.setOpaque(false);
        P4.setLayout(null);
        P4.setPreferredSize(new Dimension(280,60));
        JLabel l4 = new JLabel("Obras disponibles");
        l4.setForeground(Color.WHITE);
        l4.setBounds(5, 0, 212, 25);
        P4.add(l4);
        comboObra = new JComboBox();
        comboObra.setBounds(5, 26, 261, 28);
        
        P4.add(comboObra);
        Separator S4 = new Separator();
        S4.setBounds(5, 56, 261, 14);
        P4.add(S4);
        panel.add(P4,gbc);
        
        gbc.gridx = 0;	gbc.gridy = 1;
        JPanel P5 = new JPanel();
        P5.setOpaque(false);
        P5.setLayout(null);
        P5.setPreferredSize(new Dimension(280,60));
        JLabel l5 = new JLabel("Fecha");
        l5.setForeground(Color.WHITE);
        l5.setBounds(5, 0, 212, 25);
        P5.add(l5);
        comboFecha = new JDateChooser();
        comboFecha.setBounds(5, 26, 261, 28);
        //java.util.Date Date = comboFecha.getDate();
        
        P5.add(comboFecha);
        Separator S5 = new Separator();
        S5.setBounds(5, 56, 261, 14);
        P5.add(S5);
        panel.add(P5,gbc);
        
    }

    public JPanel PanelCentral(){
    
    	//Ancho del Panel Este y Panel Oeste.
        int PEO = 50;
        
    	panelCentral = new JPanel();
    	panelCentral.setBackground(new Color(52, 73, 94));
    	panelCentral.setLayout(new GridLayout(0,1));
        
        
        /* Panel en donde se encuentra la tabla de materiales. */
        
    	panelNorte = new JPanel();
    	panelNorte.setOpaque(false);
        panelNorte.setLayout(new BorderLayout());
        panelCentral.add(panelNorte);
        JPanel norteNorte  = new JPanel();
        norteNorte.setBackground(new Color(0, 150, 136));
        panelNorte.add(norteNorte, BorderLayout.NORTH);
        materialesDis = new labelTitulo("Materiales Disponibles");
        norteNorte.add(materialesDis);
        
        PO = new JPanel();
        PO.setOpaque(false);
        dimension(PEO,0,PO);
        panelNorte.add(PO,BorderLayout.WEST);
        JPanel norteCentro = new JPanel();
        norteCentro.setLayout(new BorderLayout());
        norteCentro.setOpaque(false);
        panelNorte.add(norteCentro, BorderLayout.CENTER);
        // Agregando la Tabla Materiales al panel norteCentro.
	    norteCentro.add(creandoTablaMat());
	    PE = new JPanel();
        PE.setBackground(new Color(52, 73, 94));
        dimension(PEO,0,PE);
        panelNorte.add(PE,BorderLayout.EAST);
	        
        
	    JPanel norteSur    = new JPanel();
	    norteSur.setLayout(new FlowLayout());
        norteSur.setOpaque(false);
        panelNorte.add(norteSur, BorderLayout.SOUTH);
        selecCantidad  = new labelForm("Seleccionar cantidad: ");
        norteSur.add(selecCantidad);
        txtCantidadSel = new txt();
        txtCantidadSel.setText("0");
        txtCantidadSel.setPreferredSize(new Dimension(1, 25));
        norteSur.add(txtCantidadSel);
        btnAgregar = new Botones("Agregar");
        //btnAgregar.setEnabled(false);
        btnAgregar.setPreferredSize(new java.awt.Dimension(90, 30));
        norteSur.add(btnAgregar);
        
        
        
        /* Panel en donde se encuentra la tabla de asignaciones. */
        
        
        panelSur = new JPanel();
        panelSur.setOpaque(false);
        panelSur.setLayout(new BorderLayout());
        panelCentral.add(panelSur);
        JPanel surNorte  = new JPanel();
        surNorte.setLayout(new BorderLayout());
        surNorte.setBackground(new Color(0, 150, 136));
        panelSur.add(surNorte, BorderLayout.NORTH);
        
        
        JPanel surNorteOeste  = new JPanel();
        surNorteOeste.setOpaque(false);
        surNorte.add(surNorteOeste, BorderLayout.WEST);
        asignarMat = new labelTitulo("      Materiales Seleccionados");
        surNorteOeste.add(asignarMat);
        JPanel surNorteEste  = new JPanel();
        surNorteEste.setOpaque(false);
        surNorte.add(surNorteEste, BorderLayout.EAST);
        JLabel obraSelec  = new JLabel("Obra 1");
        surNorteEste.add(obraSelec,BorderLayout.WEST);
        JLabel fechaSelec = new JLabel("26/05/17");
        surNorteEste.add(fechaSelec,BorderLayout.EAST);
        
        PO = new JPanel();
        PO.setBackground(new Color(52, 73, 94));
        dimension(PEO,0,PO);
        panelSur.add(PO,BorderLayout.WEST);
        JPanel surCentro = new JPanel();
        surCentro.setLayout(new BorderLayout());
        surCentro.setBackground(new Color(76, 175, 80));
        panelSur.add(surCentro, BorderLayout.CENTER);
        // Agregando la Tabla Asignaciones al panel surCentro.
        surCentro.add(creandoTablaAsig());
        PE = new JPanel();
        PE.setOpaque(false);
        dimension(PEO,0,PE);
        panelSur.add(PE,BorderLayout.EAST);
        
        
        JPanel surSur    = new JPanel();
        surSur.setOpaque(false);
        panelSur.add(surSur, BorderLayout.SOUTH);
        btnEliminarSelect = new Botones("Eliminar selección");
        //btnEliminarSelect.setEnabled(false);
        btnEliminarSelect.setPreferredSize(new java.awt.Dimension(195, 35));
        surSur.add(btnEliminarSelect);
        btnProcesarSelect = new Botones("Procesar asignación");
        //btnProcesarSelect.setEnabled(false);
        btnProcesarSelect.setPreferredSize(new java.awt.Dimension(195, 35));
        surSur.add(btnProcesarSelect);
        
    	return panelCentral;
    }
 
    private void control() {
		
    	this.btnAgregar.addActionListener(BA);
    	btnEliminarSelect.addActionListener(BA);
    	btnProcesarSelect.addActionListener(BA);
    	
    	this.txtCantidadSel.addKeyListener(TA);
	}    
    
    public AsignarDTO ObtenerDatos() {
    	
    	return new AsignarDTO(
    			
    			this.comboObra.getSelectedItem().toString(),
    			this.obtenerFecha(),
    			this.txtNombreMat.getText(),
    			Integer.parseInt(this.txtCantidadSel.getText()));
    }
    
    public String obtenerFecha() {
		
    	int year  = this.comboFecha.getCalendar().get(Calendar.YEAR);
    	int month = this.comboFecha.getCalendar().get(Calendar.MONTH)+1;
    	int day   = this.comboFecha.getCalendar().get(Calendar.DAY_OF_MONTH);
    	
    	String fecha = year+"-"+month+"-"+day;
    	
    	return fecha;
	}

    public void removerSeleccion(int filaSeleccionada) {
    	
    	this.modeloTablaAsig.removeRow(filaSeleccionada);
    }
    
    public void llenarTablaMat(Object [] datos) {
    	
    	this.modeloTablaMat.addRow(datos);
    } 

    public void llenarTablaAsig(Object [] datos) {
    	
    	this.modeloTablaAsig.addRow(datos);
    } 
    
    public void limpiar() {
		
    	comboObra.setSelectedItem(0);
    	//comboFecha.set
    	this.txtNombreMat.setText("");
    	this.txtCantidadSel.setText("");
	}
    
    public void llenarComboObra(String item) {
		
    	comboObra.addItem(item);
	}

    public void vaciar_comboObra() {
		
    	comboObra.removeAllItems();
	}
    
    private void dimension(int x, int y, JPanel p){
        
        Dimension panelD= new Dimension(x,y);
        p.setPreferredSize(panelD);
        p.setMaximumSize(panelD);
    }
    
    public JScrollPane creandoTablaMat(){
    
        modeloTablaMat  = new DefaultTableModel(datosMat,columnMat);
        scrollTablaMat  = new JScrollPane();
        tablaMateriales = new JTable();
        tablaMateriales.getTableHeader().setFont(new Font ("Acme", 0,16));
        tablaMateriales.setModel(modeloTablaMat);
        scrollTablaMat.setViewportView(tablaMateriales);
        
        return scrollTablaMat;
    }
    
    public JScrollPane creandoTablaAsig(){
        
        modeloTablaAsig    = new DefaultTableModel(datosAsig,columnAsig);
        scrollTablaAsig    = new JScrollPane();
        tablaSeleccionados = new JTable();
        tablaSeleccionados.getTableHeader().setFont(new Font ("Acme", 0,16));
        tablaSeleccionados.setModel(modeloTablaAsig);
        scrollTablaAsig.setViewportView(tablaSeleccionados);
        
        setOcultarColumnasJTable(tablaSeleccionados,new int[]{0,3});
        
        return scrollTablaAsig;
    }
    
    private void setOcultarColumnasJTable(JTable tbl, int columna[]) {
        
    	for(int i=0;i<columna.length;i++)
        {
             tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }
    
	public DefaultTableModel getModeloTablaMat() {
		return modeloTablaMat;
	}

	public void setModeloTablaMat(DefaultTableModel modeloTablaMat) {
		this.modeloTablaMat = modeloTablaMat;
	}

	public DefaultTableModel getModeloTablaAsig() {
		return modeloTablaAsig;
	}

	public void setModeloTablaAsig(DefaultTableModel modeloTablaAsig) {
		this.modeloTablaAsig = modeloTablaAsig;
	}

	public JTable getTablaSeleccionados() {
		return tablaSeleccionados;
	}

	public void setTablaSeleccionados(JTable tablaSeleccionados) {
		this.tablaSeleccionados = tablaSeleccionados;
	}

	public JTable getTablaMateriales() {
		return tablaMateriales;
	}

	public void setTablaMateriales(JTable tablaMateriales) {
		this.tablaMateriales = tablaMateriales;
	}

	public Botones getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Botones btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public Botones getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(Botones btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public Botones getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Botones btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public Botones getBtnEliminarSelect() {
		return btnEliminarSelect;
	}

	public void setBtnEliminarSelect(Botones btnEliminarSelect) {
		this.btnEliminarSelect = btnEliminarSelect;
	}

	public Botones getBtnProcesarSelect() {
		return btnProcesarSelect;
	}

	public void setBtnProcesarSelect(Botones btnProcesarSelect) {
		this.btnProcesarSelect = btnProcesarSelect;
	}

	public JComboBox getComboObra() {
		return comboObra;
	}

	public void setComboObra(JComboBox comboObra) {
		this.comboObra = comboObra;
	}

	public txt getTxtCantidadSel() {
		return txtCantidadSel;
	}

	public void setTxtCantidadSel(txt txtCantidadSel) {
		this.txtCantidadSel = txtCantidadSel;
	}

	public static void main(String args[]) {
		
		PanelAsignar PA = new PanelAsignar();
		
		JFrame frame    = new JFrame("Probando panel individual...");
		frame.setLayout(new GridLayout());
		frame.add(PA);
		frame.setSize(1300, 650);
		frame.setBackground(Color.WHITE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
            
}
