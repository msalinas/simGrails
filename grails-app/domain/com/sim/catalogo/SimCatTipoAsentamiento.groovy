package com.sim.catalogo

import com.rs.gral.RsGralAsentamiento

class SimCatTipoAsentamiento {

	String  claveTipoAsentamiento
	String  nombreTipoAsentamiento

    static constraints = {
		claveTipoAsentamiento(size:2..15, unique: true, nullable: false, blank: false)
		nombreTipoAsentamiento(size:5..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreTipoAsentamiento}"
	}
	
	static hasMany =  RsGralAsentamiento

}
