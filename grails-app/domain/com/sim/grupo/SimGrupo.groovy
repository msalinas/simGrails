package com.sim.grupo

import com.sim.empresa.RsConfEmpresa

class SimGrupo {

	String  claveGrupo
	String  nombreGrupo
	Date    fechaInicioActivacion
	RsConfEmpresa rsConfEmpresa
	
    static constraints = {
		claveGrupo(size:4..12, unique: true, nullable: false, blank: false)
		nombreGrupo(size:5..20, unique: true, nullable: false, blank: false)
		fechaInicioActivacion(nullable: false)
		rsConfEmpresa(nullable: false)
    }
	
	String toString() {
		"Grupo ${nombreGrupo}"
	}
}
