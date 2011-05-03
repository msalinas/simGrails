package com.rs.gral

import com.sim.catalogo.SimCatDescTelefono
import com.sim.empresa.RsConfEmpresa
import com.sim.empresa.RsEmpleado
import com.sim.regional.SimRegional
import com.sim.regional.SimSucursal


class RsGralTelefono {

	String  telefono
	RsConfEmpresa rsConfEmpresa
	SimCatDescTelefono descripcionTelefono
	
	static belongsTo = [ regional : SimRegional, sucursal : SimSucursal, empleado : RsEmpleado ]

    static constraints = {
		telefono(size:5..15, unique: true, nullable: false, blank: false)
		descripcionTelefono(nullable: false)
		rsConfEmpresa(nullable: false)
		regional(nullable: true)
		sucursal(nullable: true)
		empleado(nullable: true)
    }
	
	String toString() {
		"${descripcionTelefono}: ${telefono}"
	}
	
}
