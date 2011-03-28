package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa

class SimCatReporte {

	String  claveReporte
	String  nombreReporte
	RsConfEmpresa rsConfEmpresa
	String  aplicaA
	String  nombreFuncion

	static hasMany = [ simCatDocumento : SimCatDocumento ]

    static constraints = {
		claveReporte(size:5..15, unique: true, nullable: false, blank: false)
		nombreReporte(size:5..50, unique: true, nullable: false, blank: false)
		aplicaA(inList:["Individual", "Grupo"] )
		nombreFuncion(size:5..50, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }

	String toString() {
		"Reporte: ${nombreReporte}"
	}

}
