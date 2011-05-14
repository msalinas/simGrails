package com.sim.catalogo

class SimCatTasaPapel {

 	String  claveTasaPapel
	Date    fechaPublicacion
	Double  valorTasaPapel

	static belongsTo = [ tasaReferencia : SimCatTasaReferencia ]

    static constraints = {
		claveTasaPapel(size:4..15, unique: true, nullable: false, blank: false)
		fechaPublicacion()
		valorTasaPapel()
    }

	String toString() {
		"${claveTasaPapel}"
	}
}
