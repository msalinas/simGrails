package com.sim.catalogo

class SimCatLineaFondeo {
	
	String numeroLinea
	Double monto
	Double montoDisponible
	Double tasa
	Date   fechaInicio
    Date   fechaVigencia
 
	static belongsTo = [ simCatFondeador : SimCatFondeador ]

    static constraints = {
		simCatFondeador(nullable:false)
		numeroLinea(size:5..15, unique: true, nullable: false, blank: false)
		monto()
		montoDisponible()
		tasa()
		fechaInicio()
		fechaVigencia()
    }
	
	String toString() {
		"${numeroLinea}"
	}
}
