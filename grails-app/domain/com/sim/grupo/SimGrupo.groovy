package com.sim.grupo

class SimGrupo {

	String  claveGrupo
	String  nombreGrupo
	Date    fechaInicioActivacion
	
    static constraints = {
		claveGrupo(size:4..12, unique: true, nullable: false, blank: false)
		nombreGrupo(size:5..20, unique: true, nullable: false, blank: false)
		fechaInicioActivacion(nullable: false)
    }
	
	String toString() {
		"Grupo ${nombreGrupo}"
	}
}
