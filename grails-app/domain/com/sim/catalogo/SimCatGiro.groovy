package com.sim.catalogo

class SimCatGiro {
	
	String  claveGiro
	String  nombreGiro

    static constraints = {
		claveGiro(size:5..15, unique: true, nullable: false, blank: false)
		nombreGiro(size:5..200, unique: true, nullable: false, blank: false)
    }
}
