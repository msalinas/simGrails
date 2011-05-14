package com.sim.catalogo

class SimCatTipoDomicilio {

	String  claveTipoDomicilio
	String  nombreTipoDomicilio

    static constraints = {
		claveTipoDomicilio(size:5..15, unique: true, nullable: false, blank: false)
		nombreTipoDomicilio(size:5..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreTipoDomicilio}"
	}
}
