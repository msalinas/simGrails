package com.sim.catalogo

class SimCatVerificacionReferencia {

 	String  claveTipoReferencia
	String  nombreTipoReferencia
	String  descripcionTipoReferencia

    static constraints = {
		claveTipoReferencia(size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoReferencia(size:3..50, unique: true, nullable: false, blank: false)
		descripcionTipoReferencia(size:3..150, nullable: true)
    }

	String toString() {
		"${nombreTipoReferencia}"
	}
}
