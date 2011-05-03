package com.sim.empresa

import com.sim.catalogo.SimCatPuesto
import com.sim.catalogo.SimCatPerfil
import com.sim.regional.SimSucursal
import com.sim.regional.SimRegional

class RsEmpleado {
	
	Date fechaIngreso
	String numeroNomina
	Boolean esVigente = false
	Boolean asignarTodasSucursales = false
	
	static belongsTo = [puesto : SimCatPuesto, perfil : SimCatPerfil, sucursalPertenece :  SimSucursal ]
	
	static hasMany = [ sucursalesConAcceso : SimSucursal, regionalesConAcceso : SimRegional]

    static constraints = {
		puesto(nullable: true)
		perfil(nullable: true)
		sucursalPertenece(nullable: true)
		fechaIngreso()
		numeroNomina()
		esVigente()
		asignarTodasSucursales()
		sucursalesConAcceso()
		regionalesConAcceso()

    }
	
	String toString() {
		"${numeroNomina}"
	}
}
