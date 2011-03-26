package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa

class SimCatBanco {

	String  claveBanco
	String  nombreBanco
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveBanco(size:5..15, unique: true, nullable: false, blank: false)
		nombreBanco(size:5..50, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"Banco: ${nombreBanco}"
	}
}
