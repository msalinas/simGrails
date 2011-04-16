package com.sim.prueba

class City {

	String name 
	String timezone

	static belongsTo = [country:Country]
	
	String toString() {
		"${name}"
	}
}
