package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatTasaReferencia {

	String  claveTasaReferencia
	String  nombreTasaReferencia
	String  descripcionTasaReferencia
	RsConfEmpresa rsConfEmpresa

	static belongsTo = [ periodicidadTasa : SimCatPeriodicidad ]
	static hasMany = [ tasaPapel : SimCatTasaPapel ]

    static constraints = {
		claveTasaReferencia(size:4..15, unique: true, nullable: false, blank: false)
		nombreTasaReferencia(size:4..50, unique: true, nullable: false, blank: false)
		descripcionTasaReferencia()
		periodicidadTasa(nullable:false)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${nombreTasaReferencia}"
	}
}
