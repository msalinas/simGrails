package com.rs.gral

import com.sim.catalogo.SimCatDescTelefono
import com.sim.empresa.RsEmpleado
import com.sim.regional.SimRegional
import com.sim.regional.SimSucursal
import com.sim.cliente.SimClienteNegocio

class RsGralTelefono {

	String  telefono
	SimCatDescTelefono descripcionTelefono
	
	static belongsTo = [ regional : SimRegional, sucursal : SimSucursal, persona : RsPersona,
						 negocio : SimClienteNegocio ]

    static constraints = {
		telefono(size:5..15, unique: false, nullable: false, blank: false)
		descripcionTelefono(nullable: false)
		regional(nullable: true)
		sucursal(nullable: true)
		persona(nullable: true)
		negocio(nullable: true)
    }
	
	String toString() {
		"${descripcionTelefono}: ${telefono}"
	}
	
}
