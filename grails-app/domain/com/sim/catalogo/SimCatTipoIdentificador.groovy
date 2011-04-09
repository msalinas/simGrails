package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatTipoIdentificador {


	String  claveTipoIdentificador
	String  nombreTipoIdentificador
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveTipoIdentificador(size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoIdentificador(size:3..50, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${nombreTipoIdentificador}"
	}
}
