package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa

class SimCatTipoAccesorio {

	String  claveTipoAccesorio
	String  nombreTipoAccesorio
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		claveTipoAccesorio(size:5..15, unique: true, nullable: false, blank: false)
		nombreTipoAccesorio(size:5..50, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }

	static hasMany = [ simCatAccesorio : SimCatAccesorio ]
	
	String toString() {
		"${nombreTipoAccesorio}"
	}
}
