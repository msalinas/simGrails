package com.sim.catalogo

import com.rs.gral.RsPersona

class SimCatEscolaridad {
	String  claveEscolaridad
	String  nombreEscolaridad
	
	static hasMany = RsPersona

    static constraints = {
		claveEscolaridad(size:5..15, unique: true, nullable: false, blank: false)
		nombreEscolaridad(size:5..50, unique: true, nullable: false, blank: false)
    }


	String toString() {
		"${nombreEscolaridad}"
	}
}
