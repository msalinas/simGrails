package com.sim.catalogo

class SimCatPerfil {

 	String  clavePerfil
	String  nombrePerfil

    static constraints = {
		clavePerfil(size:4..15, unique: true, nullable: false, blank: false)
		nombrePerfil(size:4..25, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombrePerfil}"
	}
}
