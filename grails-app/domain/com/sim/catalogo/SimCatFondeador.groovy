package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatFondeador {
	
	String  claveFondeador
	String  nombreFondeador
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveFondeador(size:5..15, unique: true, nullable: false, blank: false)
		nombreFondeador(size:5..50, unique: true, nullable: false, blank: false)
    }
	
	static hasMany = [ simCatLineaFondeo : SimCatLineaFondeo ]
	
	String toString() {
		"${nombreFondeador}"
	}
}
