package com.sim.catalogo

class SimCatDescTelefono {

	String  claveDescripcionTelefono
	String  nombreDescripcionTelefono

    static constraints = {
		claveDescripcionTelefono(size:5..15, unique: true, nullable: false, blank: false)
		nombreDescripcionTelefono(size:3..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreDescripcionTelefono}"
	}
	
	//NO DEFINIE ATRIBUTO CON RsGralTelefono YA QUE LA RELACION ES UNO A MUCHOS
	//Y NO ES DEPENDIENTE
}
