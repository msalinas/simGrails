package com.sim.empresa

import com.sim.catalogo.SimCatPuesto
import com.sim.catalogo.SimCatPerfil
import com.sim.regional.SimSucursal
import com.sim.regional.SimRegional
import com.rs.gral.RsPersona

class RsEmpleado {
	
	Date fechaIngreso
	String numeroNomina
	Boolean esVigente = false
	Boolean asignarTodasSucursales = false
	SimCatPuesto puesto
	SimCatPerfil perfil
	
	RsPersona persona
	
	static belongsTo = [sucursalPertenece :  SimSucursal]
	
	static hasMany = [sucursalesConAcceso : SimSucursal, regionalesConAcceso : SimRegional]

	//static hasOne = [gerenteRegion :SimRegional] //Al implementar el gerente no funcionan el campo regionalesConAcceso en el dominio RsEmpleado
	
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
		//gerenteRegion(nullable: true)
    }
	
	String toString() {
		"${puesto.nombrePuesto}: ${persona.apellidoPaterno} ${persona.apellidoMaterno ?: ""} ${persona.primerNombre} ${persona.segundoNombre ?: ""}"
	}
}
