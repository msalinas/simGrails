package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatMetodoCalculo {
	
	String  claveMetodoCalculo
	String  nombreMetodoCalculo
	String	descripcionMetodoCalculo
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveMetodoCalculo(size:5..15, unique: true, nullable: false, blank: false)
		nombreMetodoCalculo(size:5..150, unique: true, nullable: false, blank: false)
		descripcionMetodoCalculo(size:0..300)
		rsConfEmpresa(nullable: false)
    }
	
	String toString() {
		"${nombreMetodoCalculo}"
	}
}
