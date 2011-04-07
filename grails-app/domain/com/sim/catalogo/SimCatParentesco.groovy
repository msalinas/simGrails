package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatParentesco {

 	String  claveParentesco
	String  nombreParentesco
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveParentesco(size:4..15, unique: true, nullable: false, blank: false)
		nombreParentesco(size:4..25, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${nombreParentesco}"
	}
}
