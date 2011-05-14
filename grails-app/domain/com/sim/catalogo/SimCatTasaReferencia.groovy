package com.sim.catalogo

class SimCatTasaReferencia {

	String  claveTasaReferencia
	String  nombreTasaReferencia
	String  descripcionTasaReferencia

	static belongsTo = [ periodicidadTasa : SimCatPeriodicidad ]
	static hasMany = [ tasaPapel : SimCatTasaPapel ]

    static constraints = {
		claveTasaReferencia(size:4..15, unique: true, nullable: false, blank: false)
		nombreTasaReferencia(size:4..50, unique: true, nullable: false, blank: false)
		descripcionTasaReferencia()
		periodicidadTasa(nullable:false)
    }


	String toString() {
		"${nombreTasaReferencia}"
	}
}
