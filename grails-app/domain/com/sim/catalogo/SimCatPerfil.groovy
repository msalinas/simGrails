package com.sim.catalogo

import com.sim.empresa.RsEmpleado

class SimCatPerfil {

 	String  clavePerfil
	String  nombrePerfil

    static constraints = {
		clavePerfil(size:4..15, unique: true, nullable: false, blank: false)
		nombrePerfil(size:4..25, unique: true, nullable: false, blank: false)
    }

	static hasMany =  RsEmpleado

	String toString() {
		"${nombrePerfil}"
	}
}
