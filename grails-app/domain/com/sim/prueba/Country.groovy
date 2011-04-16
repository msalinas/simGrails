package com.sim.prueba

class Country {

    String name
    String abbr
    String language

	static hasMany = [cities:City] 
	
	String toString() {
		"${name}"
	}
}
