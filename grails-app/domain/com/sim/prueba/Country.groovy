package com.sim.prueba

class Country {

    String nameCountry
    String abbr
    String language

	static hasMany = [cities:City] 
	
	String toString() {
		"${nameCountry}"
	}
}
