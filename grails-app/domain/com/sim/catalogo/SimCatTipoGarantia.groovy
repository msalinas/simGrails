package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatTipoGarantia {

	String  claveTipoGarantia
	String  nombreTipoGarantia
	String  descripcionTipoGarantia
	String  requisitosTipoGarantia
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveTipoGarantia(size:5..15, unique: true, nullable: false, blank: false)
		nombreTipoGarantia(size:5..50, unique: true, nullable: false, blank: false)
		descripcionTipoGarantia()
		requisitosTipoGarantia()
		rsConfEmpresa(nullable: false)
    }

	String toString() {
		"${nombreTipoGarantia}"
	}
}
