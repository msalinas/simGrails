package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa
import com.rs.gral.RsGralCodigoPostal

class SimCatTipoAsentamiento {

	String  claveTipoAsentamiento
	String  nombreTipoAsentamiento
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveTipoAsentamiento(size:2..15, unique: true, nullable: false, blank: false)
		nombreTipoAsentamiento(size:5..50, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }

	String toString() {
		"${nombreTipoAsentamiento}"
	}
	
	static hasMany = RsGralCodigoPostal, RsGralAsentamiento

}
