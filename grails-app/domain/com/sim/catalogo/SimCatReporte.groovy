package com.sim.catalogo

class SimCatReporte {

	String  claveReporte
	String  nombreReporte
	String  aplicaA
	String  nombreFuncion

    static constraints = {
		claveReporte(size:5..15, unique: true, nullable: false, blank: false)
		nombreReporte(size:5..50, unique: true, nullable: false, blank: false)
		aplicaA(inList:["INDIVIDUAL", "GRUPO"] )
		nombreFuncion(size:5..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreReporte}"
	}

}
