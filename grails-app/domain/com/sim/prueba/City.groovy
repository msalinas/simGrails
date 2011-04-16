package com.sim.prueba

class City {

	String nameCity 
	String timezone

	static belongsTo = [country:Country]
	
	String toString() {
		"${nameCity}"
	}
}
