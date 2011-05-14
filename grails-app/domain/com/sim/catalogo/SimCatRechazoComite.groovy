package com.sim.catalogo

class SimCatRechazoComite {
	String  claveRechazoComite
	String  nombreRechazoComite
	String  descripcionRechazoComite

    static constraints = {
		claveRechazoComite(size:1..15, unique: true, nullable: false, blank: false)
		nombreRechazoComite(size:5..50, unique: true, nullable: false, blank: false)
		descripcionRechazoComite(size:5..150)
    }
	
	String toString() {
		"${nombreRechazoComite}"
	}
}
