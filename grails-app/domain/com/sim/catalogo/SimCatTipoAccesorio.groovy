package com.sim.catalogo

class SimCatTipoAccesorio {

	String  claveTipoAccesorio
	String  nombreTipoAccesorio

    static constraints = {
		claveTipoAccesorio(size:3..20, unique: true, nullable: false, blank: false)
		nombreTipoAccesorio(size:3..50, unique: true, nullable: false, blank: false)
    }

	static hasMany = [ accesorios : SimCatAccesorio ]
	
	String toString() {
		"${nombreTipoAccesorio}"
	}
}
