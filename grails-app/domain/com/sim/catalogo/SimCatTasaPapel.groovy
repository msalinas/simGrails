package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatTasaPapel {

 	String  claveTasaPapel
	Date    fechaPublicacion
	Double  valorTasaPapel
	RsConfEmpresa rsConfEmpresa

	static belongsTo = [ tasaReferencia : SimCatTasaReferencia ]

    static constraints = {
		claveTasaPapel(size:4..15, unique: true, nullable: false, blank: false)
		fechaPublicacion()
		valorTasaPapel()
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"${claveTasaPapel}"
	}
}
