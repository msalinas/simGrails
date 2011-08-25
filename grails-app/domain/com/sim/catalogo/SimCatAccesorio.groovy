package com.sim.catalogo

class SimCatAccesorio {

	String  claveAccesorio
	String  nombreAccesorio
	Double  tasaIva
	Boolean beneficiario = false

	static belongsTo = [ tipoAccesorio : SimCatTipoAccesorio ]

    static constraints = {
		tipoAccesorio(nullable:false)
		claveAccesorio(size:5..15, unique: true, nullable: false, blank: false)
		nombreAccesorio(size:5..50, unique: true, nullable: false, blank: false)
		tasaIva()
		beneficiario(nullable:false)
    }

	String toString() {
		"${nombreAccesorio}"
	}
}
