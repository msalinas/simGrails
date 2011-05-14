package com.sim.catalogo

import com.rs.gral.RsPersona
import com.sim.empresa.RsClienteDocumentacion

class SimCatDocumento {

	String  claveDocumento
	String  nombreDocumento
	String  descripcion
	Boolean esReporte = false

	static belongsTo = [ simCatTipoDocumento : SimCatTipoDocumento , simCatReporte : SimCatReporte ]
	static hasMany = RsPersona, RsClienteDocumentacion

    static constraints = {
		claveDocumento(size:5..15, unique: true, nullable: false, blank: false)
		nombreDocumento(size:5..50, unique: true, nullable: false, blank: false)
		descripcion(size:10..300, unique: true, nullable: false, blank: false)
		simCatTipoDocumento(nullable:true)
		esReporte()
		simCatReporte(nullable:true)
    }

	String toString() {
		"${nombreDocumento}"
	}

}
