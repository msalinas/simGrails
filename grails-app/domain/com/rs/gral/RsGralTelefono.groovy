package com.rs.gral

import com.sim.catalogo.SimCatDescTelefono
import com.sim.empresa.RsConfEmpresa
import com.sim.regional.SimRegional

class RsGralTelefono {

	String  telefono
	RsConfEmpresa rsConfEmpresa
	SimCatDescTelefono descripcionTelefono
	
	static belongsTo = [ simRegional : SimRegional ]

    static constraints = {
		telefono(size:5..15, unique: true, nullable: false, blank: false)
		descripcionTelefono(nullable: false)
		rsConfEmpresa(nullable: false)
    }
	
	String toString() {
		"${descripcionTelefono}: ${telefono}"
	}
	
}
