package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatPeriodicidad {

 	String  clavePeriodicidad
	String  nombrePeriodicidad
	Integer cantidadPagos
	Integer numeroDias
	RsConfEmpresa rsConfEmpresa
	
	static hasMany = SimCatTasaReferencia 

    static constraints = {
		clavePeriodicidad(size:5..15, unique: true, nullable: false, blank: false)
		nombrePeriodicidad(size:5..50, unique: true, nullable: false, blank: false)
		cantidadPagos(range:1..999,nullable:false)
		numeroDias(range:1..999,nullable:false)
		rsConfEmpresa(nullable: false)
    }

	String toString() {
		"${nombrePeriodicidad}"
	}
}
