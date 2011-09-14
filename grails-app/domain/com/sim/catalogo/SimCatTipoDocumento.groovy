package com.sim.catalogo

class SimCatTipoDocumento {

	String  claveTipoDocumentacion
	String  nombreTipoDocumentacion

	static hasMany = [ simCatDocumento : SimCatDocumento ]

    static constraints = {
		claveTipoDocumentacion(size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoDocumentacion(size:3..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreTipoDocumentacion}"
	}
}
