package com.sim.catalogo

class SimCatFondeador {
	
	String  claveFondeador
	String  nombreFondeador

    static constraints = {
		claveFondeador(size:5..15, unique: true, nullable: false, blank: false)
		nombreFondeador(size:5..50, unique: true, nullable: false, blank: false)
    }
	
	static hasMany = [ simCatLineaFondeo : SimCatLineaFondeo ]
	
	String toString() {
		"${nombreFondeador}"
	}
}
