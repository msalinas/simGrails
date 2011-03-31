package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatEtapaPrestamo {

	String  claveEtapaPrestamo
	String  nombreEtapaPrestamo
	String  descripcionEtapaPrestamo
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveEtapaPrestamo(size:5..15, unique: true, nullable: false, blank: false)
		nombreEtapaPrestamo(size:5..50, unique: true, nullable: false, blank: false)
		descripcionEtapaPrestamo(size:5..250, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${nombreEtapaPrestamo}"
	}
}
