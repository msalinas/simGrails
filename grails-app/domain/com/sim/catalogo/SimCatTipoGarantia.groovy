package com.sim.catalogo

class SimCatTipoGarantia {

	String  claveTipoGarantia
	String  nombreTipoGarantia
	String  descripcionTipoGarantia
	String  requisitosTipoGarantia

    static constraints = {
		claveTipoGarantia(size:5..15, unique: true, nullable: false, blank: false)
		nombreTipoGarantia(size:5..50, unique: true, nullable: false, blank: false)
		descripcionTipoGarantia()
		requisitosTipoGarantia()
    }

	String toString() {
		"${nombreTipoGarantia}"
	}
}
