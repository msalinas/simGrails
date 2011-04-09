package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatTipoDomicilio {

	String  claveTipoDomicilio
	String  nombreTipoDomicilio
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveTipoDomicilio(size:5..15, unique: true, nullable: false, blank: false)
		nombreTipoDomicilio(size:5..50, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }

	String toString() {
		"${nombreTipoDomicilio}"
	}
}
