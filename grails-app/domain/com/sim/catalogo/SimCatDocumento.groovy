package com.sim.catalogo

class SimCatDocumento {

	String  claveDocumento
	String  nombreDocumento
	String  descripcion
	Boolean esReporte = false

	static belongsTo = [ simCatTipoDocumento : SimCatTipoDocumento , simCatReporte : SimCatReporte ]

    static constraints = {
		claveDocumento(size:5..15, unique: true, nullable: false, blank: false)
		nombreDocumento(size:5..50, nullable: false, blank: false)
		descripcion(size:10..300, nullable: true, blank: true)
		simCatTipoDocumento(nullable:true)
		esReporte()
		simCatReporte(nullable:true)
    }

	String toString() {
		"${nombreDocumento}"
	}

}
