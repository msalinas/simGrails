package com.rs.gral

import com.sim.catalogo.SimCatTipoAsentamiento

class RsGralAsentamiento {

 	String  nombreAsentamiento
	String  codigoPostal
	
	static constraints = {
		nombreAsentamiento(size:3..50, unique: false,nullable: false, blank: false)
		codigoPostal(size:5..5, unique: false,nullable: false, blank: false)
	}
	
	static belongsTo = [ delegacionMunicipio : RsGralDelegacionMunicipio, tipoAsentamiento : SimCatTipoAsentamiento ]
	
	String toString() {
		"${nombreAsentamiento}"
	}
}
