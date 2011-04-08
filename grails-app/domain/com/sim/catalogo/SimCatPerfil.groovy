package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatPerfil {

 	String  clavePerfil
	String  nombrePerfil
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		clavePerfil(size:4..15, unique: true, nullable: false, blank: false)
		nombrePerfil(size:4..25, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${nombrePerfil}"
	}
}
