package com.sim.catalogo

class SimCatAccesorio {

	String  claveAccesorio
	String  nombreAccesorio
	Double tasaIva
	Boolean beneficiario = false
    Boolean accesorio= false

	static belongsTo = [ simCatTipoAccesorio : SimCatTipoAccesorio ]

    static constraints = {
		simCatTipoAccesorio(nullable:false)
		claveAccesorio(size:5..15, unique: true, nullable: false, blank: false)
		nombreAccesorio(size:5..50, unique: true, nullable: false, blank: false)
		tasaIva()
		beneficiario(nullable:false)
		accesorio(nullable:false)
    }


	String toString() {
		"${nombreAccesorio}"
	}
}
