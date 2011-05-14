package com.sim.catalogo

class SimCatTipoNegocio {

	String  claveTipoNegocio
	String  nombreTipoNegocio
	String  descripcionTipoNegocio

    static constraints = {
		claveTipoNegocio(size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoNegocio(size:3..50, unique: true, nullable: false, blank: false)
		descripcionTipoNegocio(size:3..150, nullable: true)
    }


	String toString() {
		"${nombreTipoNegocio}"
	}
}
