/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obyhat.vista.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.freixas.jcalendar.JCalendarCombo;

import com.obyhat.controlador.BotonesUsuario;
import com.obyhat.controlador.TextoUsuarios;
import com.obyhat.modelo.dto.AsignarDTO;
import com.obyhat.modelo.dto.CategoriaDTO;
import com.obyhat.modelo.dto.UsuarioDTO;
import com.obyhat.resources.components.Botones;
import com.obyhat.resources.components.Separator;
import com.obyhat.resources.components.labelForm;
import com.obyhat.resources.components.labelTitulos;
import com.obyhat.resources.components.txt;

/**
 *
 * @author Jeis
 */
public class PanelUsuarios extends JPanel{
    
    private DefaultTableModel modeloTabla;
    private String [][] datos = {};
    private String [] column = {"Usuario","Tipo de Usuario"};
    private JTable tablaUsuarios;
    
    private JPanel panelCentral,panelIzquierdo;
    private JScrollPane scrollPane,scrollPane2;
    
    private labelForm labelNomMaterial,labelCogMaterial,labelCanDisponible,
            labelSelecCantidad;
    private txt txtNombreUsuario;
    private JPasswordField txtContrasena;
    private Separator separatorNom,separatorCod,separatorDis,separatorSel;
    private labelTitulos labelTitulo1,labelTitulo2;
    private Botones btnEliminar,btnAgregar, btnModificar,btnActualizar,btnCancelar, btnBuscar;
    private JComboBox comboUsuario;
    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    
    public static final Color btnColor = new Color(108, 122, 137);
    
    private GridBagConstraints gbc = new GridBagConstraints();
    
    private BotonesUsuario BU = new BotonesUsuario(this);
    private TextoUsuarios  TU = new TextoUsuarios(this);
    
    private void dimension(int x, int y, JPanel p){
    
        Dimension panelD= new Dimension(x,y);
        p.setPreferredSize(panelD);
        p.setMaximumSize(panelD);
    }
    
    public JScrollPane Creandotabla(JScrollPane scrollPane){
    
        modeloTabla = new DefaultTableModel(datos,column);
        
        scrollPane= new JScrollPane();
        tablaUsuarios= new JTable();
        tablaUsuarios.getTableHeader().setFont(new Font ("Acme", 0,16));// Encabezado de tablaUsuarios
        //tablaUsuarios.getTableHeader().setBackground(Color.black);
        // tablaUsuarios.getTableHeader().setForeground(Color.BLUE);
        //tablaUsuarios.getTableHeader().setReorderingAllowed(false);// No permite que se muevan las columnas
        tablaUsuarios.setModel(modeloTabla);
        scrollPane.setViewportView(tablaUsuarios);
        
        return scrollPane;
    }
    
    
    /*public void LimpiarCampos(){
        
        txtNombreMat.setText("");
        txtCodigoMat.setText("");
        txtCantidadDis.setText("");
        txtSelectCant.setText("");
        comboUsuario.setSelectedIndex(0);
    }*/
    
    public PanelUsuarios(){
    	
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
        

    	BU.obtenerTiposdeUsuarios();
    	BU.obtenerUsuarios();
     }      
    
    private void PanelIzquierdo(){
    
    	panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(new Color(46,64,83));
        panelIzquierdo.setPreferredSize(new Dimension(300,0));
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.setBorder(new LineBorder(new Color(42, 59, 80),3,true));
        
        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelIzquierdo.add(panel1, BorderLayout.NORTH);
        
        labelTitulos l1 = new labelTitulos("  Registrar usuarios");
        panel1.add(l1);
        
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        panelIzquierdo.add(panel);
        gbc.insets = new Insets(5,0,5,0);
        
        gbc.gridx = 0;	gbc.gridy = 3;
        JPanel P2 = new JPanel();
        P2.setOpaque(false);
        P2.setLayout(null);
        P2.setPreferredSize(new Dimension(280,60));
        JLabel l2 = new JLabel("Usuario");
        l2.setForeground(Color.WHITE);
        l2.setBounds(5, 0, 212, 25);
        P2.add(l2);
        txtNombreUsuario = new txt();
        txtNombreUsuario.setBounds(5, 26, 261, 28);
        P2.add(txtNombreUsuario);
        Separator S2 = new Separator();
        S2.setBounds(5, 56, 261, 14);
        P2.add(S2);
        panel.add(P2,gbc);
        
        gbc.gridx = 0;	gbc.gridy = 4;
        JPanel P3 = new JPanel();
        P3.setOpaque(false);
        P3.setLayout(null);
        P3.setPreferredSize(new Dimension(280,60));
        JLabel l3 = new JLabel("Contrasena");
        l3.setForeground(Color.WHITE);
        l3.setBounds(5, 0, 212, 25);
        P3.add(l3);
        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(5, 26, 261, 28);
        P3.add(txtContrasena);
        Separator S3 = new Separator();
        S3.setBounds(5, 56, 261, 14);
        P3.add(S3);
        panel.add(P3,gbc);
        
        gbc.gridx = 0;	gbc.gridy = 5;
        JPanel P4 = new JPanel();
        P4.setOpaque(false);
        P4.setLayout(null);
        P4.setPreferredSize(new Dimension(280,60));
        JLabel l4 = new JLabel("Tipo de usuario");
        l4.setForeground(Color.WHITE);
        l4.setBounds(5, 0, 212, 25);
        P4.add(l4);
        comboUsuario = new JComboBox();
        comboUsuario.setBounds(5, 26, 261, 28);
        P4.add(comboUsuario);
        Separator S4 = new Separator();
        S4.setBounds(5, 56, 261, 14);
        P4.add(S4);
        panel.add(P4,gbc);
        
        gbc.insets = new Insets(45,0,5,0);
        
     // JPanel Contenedor de botones
        gbc.gridx = 0;	gbc.gridy = 6;
        JPanel PCB = new JPanel();
        //PCB.setPreferredSize(new Dimension(300, 50));
        //PCB.setBounds(10,24,850, 50);
        PCB.setOpaque(false);
        PCB.setLayout(new FlowLayout());
        panel.add(PCB,gbc);
        
            btnAgregar = new Botones("Agregar");
            //btnAgregar.setEnabled(false);
            btnAgregar.setPreferredSize(new java.awt.Dimension(90, 35));
            btnAgregar.addActionListener(BU);
            PCB.add(btnAgregar);

    		btnActualizar = new Botones("Actualizar");
    		btnActualizar.setEnabled(false);
    		btnActualizar.setPreferredSize(new java.awt.Dimension(90, 35));
    		btnActualizar.addActionListener(BU);
    		PCB.add(btnActualizar);

    		btnCancelar = new Botones("Cancelar");
    		//btnCancelar.setEnabled(false);
    		btnCancelar.setPreferredSize(new java.awt.Dimension(90, 35));
    		btnCancelar.addActionListener(BU);
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
            labelTitulo2 = new labelTitulos("Usuarios Registrados");
            labelTitulo2.setBounds(23, 47, 295, 25);
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
        PS.setLayout(null);
        PS.setBackground(new Color(52, 73, 94));
        dimension(0,PNS,PS);
        
        //JPanel Contenedor de botones
        JPanel PCB = new JPanel();
        PCB.setBounds(10,24,850, 50);
        PCB.setOpaque(false);
        PCB.setLayout(new FlowLayout(FlowLayout.LEFT));
        
           	btnModificar = new Botones("Modificar usuario");
            //btnModificar.setEnabled(false);
            btnModificar.setPreferredSize(new java.awt.Dimension(180, 35));
            btnModificar.addActionListener(BU);
            PCB.add(btnModificar);

            btnEliminar = new Botones("Eliminar usuario");
            //btnEliminar.setEnabled(false);
            btnEliminar.setPreferredSize(new java.awt.Dimension(180, 35));
            btnEliminar.addActionListener(BU);
            PCB.add(btnEliminar);
            PS.add(PCB);
            
        panelCentral.add(PS,BorderLayout.SOUTH);
    }

    public void llenarFormulario(UsuarioDTO datos) {
    	
    	txtNombreUsuario.setText(datos.getNombre());
    	txtContrasena.setText(datos.getContrasena());
    	comboUsuario.setSelectedIndex(datos.getIdTipoUsuario()-1);
    }
     
    
    public void limpiarTabla() {
    	
    	for (int i = 0; i < tablaUsuarios.getRowCount(); i++) {
            
            getModeloTabla().removeRow(i);
            i-=1;
        }
    }
    
    public final void insertarTabla(Object data[]){
    	
    	this.modeloTabla.addRow(data);
    }
    
    
    public void limpiar() {
		
    	txtNombreUsuario.setText("");
    	txtContrasena.setText("");
    	comboUsuario.setSelectedIndex(0);
	}
    
    public UsuarioDTO ObtenerDatos() {
    	
    	return new UsuarioDTO(

    			this.txtNombreUsuario.getText(),
    			this.obtenerPassword(),
    			this.comboUsuario.getSelectedIndex()+1);
    }
   
    public String obtenerPassword() {
    	
    	char[] arrayC = txtContrasena.getPassword(); 
    	String pass = new String(arrayC); 
    	
    	return pass;
    }
   
    public void llenar_comboUsuario(String item) {
		
    	comboUsuario.addItem(item);
	}
    
    public void vaciar_comboUsuario() {
		
    	comboUsuario.removeAllItems();
	}
    
    public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}

	public void setModeloTabla(DefaultTableModel modeloTabla) {
		this.modeloTabla = modeloTabla;
	}

	public JTable getTablaUsuarios() {
		return tablaUsuarios;
	}

	public void setTablaUsuarios(JTable tablaUsuarios) {
		this.tablaUsuarios = tablaUsuarios;
	}

	public txt getTxtNombreUsuario() {
		return txtNombreUsuario;
	}

	public void setTxtNombreUsuario(txt txtNombreUsuario) {
		this.txtNombreUsuario = txtNombreUsuario;
	}

	public Botones getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(Botones btnEliminar) {
		this.btnEliminar = btnEliminar;
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

	public Botones getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(Botones btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JComboBox getComboUsuario() {
		return comboUsuario;
	}

	public void setComboUsuario(JComboBox comboUsuario) {
		this.comboUsuario = comboUsuario;
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Probando panel individual...");
		frame.setLayout(new GridLayout());
		PanelUsuarios PA = new PanelUsuarios();
		frame.add(PA);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setSize(1300, 650);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
            
}
