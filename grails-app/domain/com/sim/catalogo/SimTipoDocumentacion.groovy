package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa

class SimTipoDocumentacion {

	String  claveTipoDocumentacion
	String  nombreTipoDocumentacion
	RsConfEmpresa rsConfEmpresa

	static hasMany = [ simCatDocumento : SimCatDocumento ]

    static constraints = {
		claveTipoDocumentacion(size:5..15, unique: true, nullable: false, blank: false)
		nombreTipoDocumentacion(size:5..50, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"Tipo Documentacion: ${nombreTipoDocumentacion}"
	}
}
