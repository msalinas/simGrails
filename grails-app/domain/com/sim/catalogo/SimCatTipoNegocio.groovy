package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatTipoNegocio {

	String  claveTipoNegocio
	String  nombreTipoNegocio
	String  descripcionTipoNegocio
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveTipoNegocio(size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoNegocio(size:3..50, unique: true, nullable: false, blank: false)
		descripcionTipoNegocio(size:3..150, nullable: true)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${nombreTipoNegocio}"
	}
}
