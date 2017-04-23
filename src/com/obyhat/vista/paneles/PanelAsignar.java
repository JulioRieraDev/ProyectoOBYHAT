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
import java.sql.Date;
import java.util.Calendar;

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
import com.obyhat.resources.components.labelTitulos;
import com.obyhat.resources.components.txt;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

/**
 *
 * @author Jeis
 */
public class PanelAsignar extends JPanel{
    
    private DefaultTableModel modeloTabla;
    private String [][] datos = {};
    private String [] column = {"Obra","Fecha","Material","Cantidad Seleccionada"};
    private JTable tablaSeleccionados;
    
    private JPanel panelCentral,panelIzquierdo;
    private JScrollPane scrollPane,scrollPane2;
    
    private labelForm labelNomMaterial,labelCogMaterial,labelCanDisponible,
            labelSelecCantidad;
    private txt txtNombreMat,txtCodigoMat,txtCantidadDis,txtCantidadSel;
    private Separator separatorNom,separatorCod,separatorDis,separatorSel;
    private labelTitulos labelTitulo1,labelTitulo2;
    private Botones btnBuscar,btnLimpiar,btnAgregar,btnEditarSelect,btnActualizar,btnCancelar,
            btnEliminarSelect,btnProcesarSelect;
    private JComboBox comboObra;
    
    private JDateChooser comboFecha;
    
    private Date sqlDate;
    
    private BotonesAsignar BA = new BotonesAsignar(this);
    private TextoAsignar   TA = new TextoAsignar(this);
    
    private GridBagConstraints gbc = new GridBagConstraints();
    
    private void dimension(int x, int y, JPanel p){
    
        Dimension panelD= new Dimension(x,y);
        p.setPreferredSize(panelD);
        p.setMaximumSize(panelD);
    }
    
    public JScrollPane Creandotabla(JScrollPane scrollPane){
    
        modeloTabla = new DefaultTableModel(datos,column);
        
        scrollPane= new JScrollPane();
        tablaSeleccionados= new JTable();
        tablaSeleccionados.getTableHeader().setFont(new Font ("Acme", 0,16));// Encabezado de tablaSeleccionados
        //tablaSeleccionados.getTableHeader().setBackground(Color.black);
        // tablaSeleccionados.getTableHeader().setForeground(Color.BLUE);
        //tablaSeleccionados.getTableHeader().setReorderingAllowed(false);// No permite que se muevan las columnas
        tablaSeleccionados.setModel(modeloTabla);
        scrollPane.setViewportView(tablaSeleccionados);
        
        return scrollPane;
    }
    
    
    public void LimpiarCampos(){
        
        txtNombreMat.setText("");
        txtCodigoMat.setText("");
        txtCantidadDis.setText("");
        txtCantidadSel.setText("");
        comboObra.setSelectedIndex(0);
    }
    
    public PanelAsignar(){
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
        
        this.BA.obtenerObras();
     }      
    
    private void PanelIzquierdo(){
    
    	panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(new Color(46,64,83));
        panelIzquierdo.setPreferredSize(new Dimension(300,0));
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.setBorder(new LineBorder(new Color(42, 59, 80),3,true));
        
        JPanel panel = new JPanel();
        panel.setOpaque(false);
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
        comboObra.addMouseListener(TA);
        
        Component[] components = this.comboObra.getComponents();
        for(final Component component : components) {
            component.addMouseListener(TA);
        }
        this.comboObra.getEditor().getEditorComponent().addMouseListener(TA);
        
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
        
        gbc.gridx = 0;	gbc.gridy = 2;
        JPanel P1 = new JPanel();
        P1.setOpaque(false);
        P1.setLayout(null);
        P1.setPreferredSize(new Dimension(280,60));
        JLabel l1 = new JLabel("Nombre del material");
        l1.setForeground(Color.WHITE);
        l1.setBounds(5, 0, 212, 25);
        P1.add(l1);
        txtNombreMat = new txt();
        txtNombreMat.setBounds(5, 26, 261, 28);
        P1.add(txtNombreMat);
        Separator S1 = new Separator();
        S1.setBounds(5, 56, 261, 14);
        P1.add(S1);
        panel.add(P1,gbc);
        
        gbc.gridx = 0;	gbc.gridy = 3;
        JPanel P2 = new JPanel();
        P2.setOpaque(false);
        P2.setLayout(null);
        P2.setPreferredSize(new Dimension(280,60));
        JLabel l2 = new JLabel("Cantidad disponible");
        l2.setForeground(Color.WHITE);
        l2.setBounds(5, 0, 212, 25);
        P2.add(l2);
        txtCantidadDis = new txt();
        txtCantidadDis.setText("20");
        txtCantidadDis.setEditable(false);
        txtCantidadDis.setBounds(5, 26, 261, 28);
        P2.add(txtCantidadDis);
        Separator S2 = new Separator();
        S2.setBounds(5, 56, 261, 14);
        P2.add(S2);
        panel.add(P2,gbc);
        
        gbc.gridx = 0;	gbc.gridy = 4;
        JPanel P3 = new JPanel();
        P3.setOpaque(false);
        P3.setLayout(null);
        P3.setPreferredSize(new Dimension(280,60));
        JLabel l3 = new JLabel("Seleccione cantidad");
        l3.setForeground(Color.WHITE);
        l3.setBounds(5, 0, 212, 25);
        P3.add(l3);
        txtCantidadSel = new txt();
        txtCantidadSel.setBounds(5, 26, 261, 28);
        txtCantidadSel.addKeyListener(TA);
        P3.add(txtCantidadSel);
        Separator S3 = new Separator();
        S3.setBounds(5, 56, 261, 14);
        P3.add(S3);
        panel.add(P3,gbc);
        
        gbc.insets = new Insets(45,0,5,0);
        
        // JPanel Contenedor de botones
           gbc.gridx = 0;	gbc.gridy = 5;
           JPanel PCB = new JPanel();
           //PCB.setPreferredSize(new Dimension(300, 50));
           //PCB.setBounds(10,24,850, 50);
           PCB.setOpaque(false);
           PCB.setLayout(new FlowLayout());
           panel.add(PCB,gbc);
           
           btnAgregar = new Botones("Agregar");
           //btnAgregar.setEnabled(false);
           btnAgregar.setPreferredSize(new java.awt.Dimension(90, 35));
           btnAgregar.addActionListener(BA);
           PCB.add(btnAgregar);

       		btnActualizar = new Botones("Actualizar");
       		btnActualizar.setEnabled(false);
       		btnActualizar.setPreferredSize(new java.awt.Dimension(90, 35));
       		btnActualizar.addActionListener(BA);
       		PCB.add(btnActualizar);

       		btnCancelar = new Botones("Cancelar");
       		//btnCancelar.setEnabled(false);
       		btnCancelar.setPreferredSize(new java.awt.Dimension(90, 35));
       		btnCancelar.addActionListener(BA);
       		PCB.add(btnCancelar);
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
        PN.setLayout(new BorderLayout());
        PN.setBackground(new Color(52, 73, 94));
        dimension(0,PNS,PN);
            labelTitulo2 = new labelTitulos("Materiales Seleccionados");
            labelTitulo2.setBounds(23, 47, 295, 25);
            panelCentral.add(labelTitulo2);
        panelCentral.add(PN,BorderLayout.NORTH);
        
        JPanel PN1 = new JPanel();
        PN1.setOpaque(false);
        PN1.setPreferredSize(new Dimension(0, 60));
        /*buscar = new labelForm("Buscar por nombre: ");
        PN1.add(buscar);
        txtBuscar = new txt();
        txtBuscar.addKeyListener(CT);
		txtBuscar.setPreferredSize(new Dimension(250, 28));
		PN1.add(txtBuscar);*/
		btnBuscar = new Botones("<---  Actualizar combo obras");
		btnBuscar.setPreferredSize(new Dimension(285, 35));
        btnBuscar.addActionListener(BA);
		PN1.add(btnBuscar);
        PN.add(PN1, BorderLayout.SOUTH);
        
        
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
        PS.setLayout(null);
        PS.setBackground(new Color(52, 73, 94));
        dimension(0,PNS,PS);
        
            // JPanel Contenedor de botones
            JPanel PCB = new JPanel();
            PCB.setBounds(10,24,850, 50);
            PCB.setOpaque(false);
            PCB.setLayout(new FlowLayout(FlowLayout.LEFT));
            
                btnEditarSelect = new Botones("Editar selección");
                //btnEditarSelect.setEnabled(false);
                btnEditarSelect.setPreferredSize(new java.awt.Dimension(195, 35));
                btnEditarSelect.addActionListener(BA);
                PCB.add(btnEditarSelect);

                btnEliminarSelect = new Botones("Eliminar selección");
                //btnEliminarSelect.setEnabled(false);
                btnEliminarSelect.setPreferredSize(new java.awt.Dimension(195, 35));
                btnEliminarSelect.addActionListener(BA);
                PCB.add(btnEliminarSelect);

                btnProcesarSelect = new Botones("Procesar asignación");
                //btnProcesarSelect.setEnabled(false);
                btnProcesarSelect.setPreferredSize(new java.awt.Dimension(195, 35));
                btnProcesarSelect.addActionListener(BA);
                PCB.add(btnProcesarSelect);
            PS.add(PCB);
        panelCentral.add(PS,BorderLayout.SOUTH);
    }

    public AsignarDTO ObtenerDatos() {
    	
    	return new AsignarDTO(
    			
    			this.comboObra.getSelectedItem().toString(),
    			this.obtenerFecha(),
    			this.txtNombreMat.getText(),
    			Integer.parseInt(this.txtCantidadDis.getText()),
    			Integer.parseInt(this.txtCantidadSel.getText()));
    }
    
    public String obtenerFecha() {
		
    	int year  = this.comboFecha.getCalendar().get(Calendar.YEAR);
    	int month = this.comboFecha.getCalendar().get(Calendar.MONTH)+1;
    	int day   = this.comboFecha.getCalendar().get(Calendar.DAY_OF_MONTH);
    	
    	String fecha = year+"-"+month+"-"+day;
    	
    	return fecha;
	}
    
   public void llenarFormulario(String[] datosTabla ) {
    	
    	this.txtNombreMat.setText(datosTabla[0]);
    	this.txtCantidadSel.setText(datosTabla[1]);
    }

    public void removerSeleccion(int filaSeleccionada) {
    	
    	this.modeloTabla.removeRow(filaSeleccionada);
    }
    
    public void llenarTabla(Object [] datos) {
    	
    	this.modeloTabla.addRow(datos);
    } 
    
    
    
    public void limpiar() {
		
    	comboObra.setSelectedItem(0);
    	//comboFecha.set
    	this.txtNombreMat.setText("");
    	this.txtCantidadDis.setText("");
    	this.txtCantidadSel.setText("");
	}
    
    public void llenar_comboObra(String item) {
		
    	comboObra.addItem(item);
	}
    
    public void vaciar_comboObra() {
		
    	comboObra.removeAllItems();
	}
    
    
    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public JTable getTablaSeleccionados() {
        return tablaSeleccionados;
    }

    public void setTablaSeleccionados(JTable tablaSeleccionados) {
        this.tablaSeleccionados = tablaSeleccionados;
    }

    public txt getTxtNombreMat() {
        return txtNombreMat;
    }

    public void setTxtNombreMat(txt txtNombreMat) {
        this.txtNombreMat = txtNombreMat;
    }

    public txt getTxtCodigoMat() {
        return txtCodigoMat;
    }

    public void setTxtCodigoMat(txt txtCodigoMat) {
        this.txtCodigoMat = txtCodigoMat;
    }

    public txt getTxtCantidadDis() {
        return txtCantidadDis;
    }

    public void setTxtCantidadDis(txt txtCantidadDis) {
        this.txtCantidadDis = txtCantidadDis;
    }

    public txt getTxtCantidadSel() {
        return txtCantidadSel;
    }

    public void setTxtCantidadSelxtSelectCant(txt txtSelectCant) {
        this.txtCantidadSel = txtSelectCant;
    }

    public labelTitulos getLabelTitulo1() {
        return labelTitulo1;
    }

    public void setLabelTitulo1(labelTitulos labelTitulo1) {
        this.labelTitulo1 = labelTitulo1;
    }

    public Botones getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(Botones btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public Botones getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(Botones btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public Botones getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(Botones btnAgregar) {
        this.btnAgregar = btnAgregar;
    }

    public Botones getBtnEditarSelect() {
        return btnEditarSelect;
    }

    public void setBtnEditarSelect(Botones btnEditarSelect) {
        this.btnEditarSelect = btnEditarSelect;
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


	public JDateChooser getComboFecha() {
		return comboFecha;
	}

	public void setComboFecha(JDateChooser comboFecha) {
		this.comboFecha = comboFecha;
	}

	public JComboBox getComboObra() {
		return comboObra;
	}

	public void setComboObra(JComboBox comboObra) {
		this.comboObra = comboObra;
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Probando panel individual...");
		frame.setLayout(new GridLayout());
		PanelAsignar PA = new PanelAsignar();
		frame.add(PA);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setSize(1300, 650);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
            
}
