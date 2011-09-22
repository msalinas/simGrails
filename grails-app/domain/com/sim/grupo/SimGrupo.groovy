package com.sim.grupo

import com.sim.cliente.RsCliente

class SimGrupo {

	String  claveGrupo
	String  nombreGrupo
	Date    fechaInicioActivacion
	
	static hasMany = [ integrantes : RsCliente ]
	
    static constraints = {
		claveGrupo(size:3..12, unique: true, nullable: false, blank: false)
		nombreGrupo(size:3..20, unique: true, nullable: false, blank: false)
		fechaInicioActivacion(nullable: false)
		integrantes()
    }
	
	String toString() {
		"Grupo ${nombreGrupo}"
	}
}
