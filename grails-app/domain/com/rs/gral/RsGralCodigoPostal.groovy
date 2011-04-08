package com.rs.gral

import com.sim.catalogo.SimCatTipoAsentamiento

class RsGralCodigoPostal {

	String  codigoPostal
 	String  codigo
	String  asentamiento
	String  municipio
	String  estado
	String  ciudad
	String  oficina
	
	static belongsTo = [ tipoAsentamiento : SimCatTipoAsentamiento ]
	
	static constraints = {
		codigoPostal(size:5..5, nullable: false, blank: false)
		codigo()
		tipoAsentamiento(nullable: false)
		asentamiento(nullable: false)
		ciudad()
		estado()
		municipio()
		oficina()
	}
	
	String toString() {
		"${codigoPostal}"
	}
	
}
