package com.sim.empresa

import com.sim.usuario.Usuario
import com.sim.catalogo.SimCatPuesto
import com.sim.catalogo.SimCatPerfil
import com.sim.regional.SimSucursal
import com.sim.regional.SimRegional
import com.rs.gral.RsGralTelefono

class RsEmpleado {
	
	RsConfEmpresa rsConfEmpresa
	Usuario usuario
	Date fechaIngreso
	String numeroNomina
	Boolean esVigente = false
	Boolean asignarTodasSucursales = false
	
	static mapping = {
		usuario lazy:false
	}
	
	static belongsTo = [puesto : SimCatPuesto, perfil : SimCatPerfil, sucursalPertenece :  SimSucursal ]
	
	static hasMany = [ sucursalesConAcceso : SimSucursal, regionalesConAcceso : SimRegional, telefonos : RsGralTelefono ]

    static constraints = {
		usuario(nullable:false)
		puesto(nullable: true)
		perfil(nullable: true)
		sucursalPertenece(nullable: true)
		fechaIngreso()
		numeroNomina()
		esVigente()
		asignarTodasSucursales()
		sucursalesConAcceso()
		regionalesConAcceso()
		rsConfEmpresa(nullable: false)
    }
	
	String toString() {
		"${usuario}"
	}
}
