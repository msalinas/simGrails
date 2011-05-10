package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;
import com.rs.gral.RsPersona

class SimCatEscolaridad {
	String  claveEscolaridad
	String  nombreEscolaridad
	RsConfEmpresa rsConfEmpresa
	
	static hasMany = RsPersona

    static constraints = {
		claveEscolaridad(size:5..15, unique: true, nullable: false, blank: false)
		nombreEscolaridad(size:5..50, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${nombreEscolaridad}"
	}
}
