package com.sim.catalogo

class SimCatParentesco {

 	String  claveParentesco
	String  nombreParentesco

    static constraints = {
		claveParentesco(size:4..15, unique: true, nullable: false, blank: false)
		nombreParentesco(size:4..25, unique: true, nullable: false, blank: false)
    }


	String toString() {
		"${nombreParentesco}"
	}
}
