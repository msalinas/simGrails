package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;
import com.rs.gral.RsPersona

class SimCatTipoPersona {

 	String  claveTipoPersona
	String  nombreTipoPersona
	String  descripcionTipoPersona
	RsConfEmpresa rsConfEmpresa
	
	static hasMany = RsPersona

    static constraints = {
		claveTipoPersona(size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoPersona(size:3..50, unique: true, nullable: false, blank: false)
		descripcionTipoPersona(size:3..150, nullable: true)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${nombreTipoPersona}"
	}
}
