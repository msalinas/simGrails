package com.sim.catalogo

class SimCatTipoAccesorio {

	String  claveTipoAccesorio
	String  nombreTipoAccesorio

    static constraints = {
		claveTipoAccesorio(size:5..15, unique: true, nullable: false, blank: false)
		nombreTipoAccesorio(size:5..50, unique: true, nullable: false, blank: false)
    }

	static hasMany = [ accesorios : SimCatAccesorio ]
	
	String toString() {
		"${nombreTipoAccesorio}"
	}
}
