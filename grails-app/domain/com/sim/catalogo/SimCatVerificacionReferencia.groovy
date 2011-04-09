package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatVerificacionReferencia {

 	String  claveTipoReferencia
	String  nombreTipoReferencia
	String  descripcionTipoReferencia
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveTipoReferencia(size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoReferencia(size:3..50, unique: true, nullable: false, blank: false)
		descripcionTipoReferencia(size:3..150, nullable: true)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${nombreTipoReferencia}"
	}
}
