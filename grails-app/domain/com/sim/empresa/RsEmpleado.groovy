package com.sim.empresa

import com.sim.catalogo.SimCatPuesto
import com.sim.catalogo.SimCatPerfil
import com.sim.regional.SimSucursal
import com.sim.regional.SimRegional
import com.rs.gral.RsPersona

class RsEmpleado {
	
	Date    fechaIngreso
	String  numeroNomina
	Boolean esVigente = false
	Boolean asignarTodasSucursales = false
	
	SimCatPuesto puesto
	SimCatPerfil perfil
	RsPersona    persona
	SimSucursal  sucursalPertenece
	
	//RELACION MUCHOS A MUCHOS RsEmpleado Y SimRegional
	static hasMany = [sucursalesConAcceso : SimSucursal, regionalesConAcceso : SimRegional]
	//SE MAPEA QUE EL ATRIBUTO regionalesConAcceso CON EL ATRIBUTO DE empleados DEL DOMINIO SimRegional
	static mappedBy = [regionalesConAcceso:"empleados"]

    static constraints = {
		persona(unique: true)
		puesto(nullable: false)
		perfil(nullable: false)
		sucursalPertenece(nullable: false)
		fechaIngreso()
		numeroNomina nullable:false, blank: false
		esVigente()
		asignarTodasSucursales()
		sucursalesConAcceso()
		regionalesConAcceso()
    }
	
	String toString() {
		"${puesto.nombrePuesto}: ${persona.apellidoPaterno} ${persona.apellidoMaterno ?: ""} ${persona.primerNombre} ${persona.segundoNombre ?: ""}"
	}
}
