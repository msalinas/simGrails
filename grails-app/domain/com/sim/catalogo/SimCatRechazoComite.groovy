package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatRechazoComite {
	String  claveRechazoComite
	String  nombreRechazoComite
	String  descripcionRechazoComite
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveRechazoComite(size:1..15, unique: true, nullable: false, blank: false)
		nombreRechazoComite(size:5..50, unique: true, nullable: false, blank: false)
		descripcionRechazoComite(size:5..150)
		rsConfEmpresa(nullable: false)
    }
	
	String toString() {
		"${nombreRechazoComite}"
	}
}
