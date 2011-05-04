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
	
	static belongsTo = [puesto : SimCatPuesto, perfil : SimCatPerfil, sucursalPertenece :  SimSucursal, persona : RsPersona ]
	
	static hasMany = [ sucursalesConAcceso : SimSucursal, regionalesConAcceso : SimRegional]

    static constraints = {
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
		"${numeroNomina}"
	}
}
