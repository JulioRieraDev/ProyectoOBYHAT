package com.obyhat.modelo.dto;

import java.sql.Date;

public class AsignarDTO {

	private int 	idOrdenSalidad;
	private String  obra;
	private String 	fechaAsignacion;
	private String 	nombreMaterial;
	private int 	cantidadDisponible;
	private int 	cantidadSeleccionada;
	
	
	
	public AsignarDTO(String nombreMaterial, int cantidadDisponible) {
		
		this.nombreMaterial = nombreMaterial;
		this.cantidadSeleccionada = cantidadDisponible;
	}


	public AsignarDTO(String fechaAsignacion, String nombreMaterial, int cantidadSeleccionada) {
		
		this.fechaAsignacion = fechaAsignacion;
		this.nombreMaterial = nombreMaterial;
		this.cantidadSeleccionada = cantidadSeleccionada;
	}

	
	public AsignarDTO(String obra, String fechaAsignacion, String nombreMaterial,
			int cantidadSeleccionada) {
		
		this.obra = obra;
		this.fechaAsignacion = fechaAsignacion;
		this.nombreMaterial = nombreMaterial;
		this.cantidadSeleccionada = cantidadSeleccionada;
	}

	
	public AsignarDTO(int idOrdenSalidad, String obra, String fechaAsignacion, String nombreMaterial,
			int cantidadDisponible, int cantidadSeleccionada) {
		
		this.idOrdenSalidad = idOrdenSalidad;
		this.obra = obra;
		this.fechaAsignacion = fechaAsignacion;
		this.nombreMaterial = nombreMaterial;
		this.cantidadDisponible = cantidadDisponible;
		this.cantidadSeleccionada = cantidadSeleccionada;
	}

	public final Object[] toArray(){
        Object[] datos = new Object[5];
        
        //datos[0] = this.idOrdenSalidad;
        datos[0] = this.obra;
        datos[1] = this.fechaAsignacion;
        datos[2] = this.nombreMaterial;
        datos[3] = this.cantidadDisponible;
        datos[4] = this.cantidadSeleccionada;
        
        return datos;
    }

	public int getIdOrdenSalidad() {
		return idOrdenSalidad;
	}


	public void setIdOrdenSalidad(int idOrdenSalidad) {
		this.idOrdenSalidad = idOrdenSalidad;
	}


	public String getObra() {
		return obra;
	}


	public void setObra(String obra) {
		this.obra = obra;
	}

	public String getFechaAsignacion() {
		return fechaAsignacion;
	}


	public void setFechaAsignacion(String fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}


	public String getNombreMaterial() {
		return nombreMaterial;
	}


	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}


	public int getCantidadDisponible() {
		return cantidadDisponible;
	}


	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}


	public int getCantidadSeleccionada() {
		return cantidadSeleccionada;
	}


	public void setCantidadSeleccionada(int cantidadSeleccionada) {
		this.cantidadSeleccionada = cantidadSeleccionada;
	}
}
