package com.sim.producto

import com.sim.catalogo.SimCatAccesorio

class SimProductoCicloAccesorios implements Comparable{

	Integer orden
	
	SimCatAccesorio accesorio
	
	static belongsTo = [ productoCiclo : SimProductoCiclo ]
	
    static constraints = {
		productoCiclo()
		accesorio unique : true
		orden range:1..100, unique : true
    }
	
	String toString() {
		"ORDEN: ${orden}: ${accesorio.nombreAccesorio}"
	}
	
	int compareTo(obj) {
		orden.compareTo(obj.orden)
	}

}
