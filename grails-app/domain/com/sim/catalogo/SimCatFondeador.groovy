package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatFondeador {
	
	String  numeroLinea
	Double monto
	Double montoDisponible
	Double tasa
	Date   fechaInicio
    Date   fechaVigencia
	RsConfEmpresa rsConfEmpresa
 

    static constraints = {
		numeroLinea(size:5..15, unique: true, nullable: false, blank: false)
		monto()
		montoDisponible()
		tasa()
		fechaInicio()
		fechaVigencia()
		rsConfEmpresa(nullable: false)
    }
}
