package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa

class SimCatAccesorio {

	String  claveAccesorio
	String  nombreAccesorio
	RsConfEmpresa rsConfEmpresa
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
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"Accesorio ${nombreAccesorio}"
	}
}
