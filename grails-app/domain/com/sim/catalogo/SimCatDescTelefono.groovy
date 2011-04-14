package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa

class SimCatDescTelefono {

	String  claveDescripcionTelefono
	String  nombreDescripcionTelefono
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveDescripcionTelefono(size:5..15, unique: true, nullable: false, blank: false)
		nombreDescripcionTelefono(size:3..50, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${nombreDescripcionTelefono}"
	}
}
