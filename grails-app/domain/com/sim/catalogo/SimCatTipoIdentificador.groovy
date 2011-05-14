package com.sim.catalogo

class SimCatTipoIdentificador {

	String  claveTipoIdentificador
	String  nombreTipoIdentificador

    static constraints = {
		claveTipoIdentificador(size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoIdentificador(size:3..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreTipoIdentificador}"
	}
}
