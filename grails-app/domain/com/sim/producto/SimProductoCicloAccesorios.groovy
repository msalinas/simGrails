package com.sim.producto

import com.sim.catalogo.SimCatAccesorio

class SimProductoCicloAccesorios {

	Integer orden
	
	SimCatAccesorio accesorio
	
	static belongsTo = [ productoCiclo : SimProductoCiclo ]
	
    static constraints = {
		productoCiclo()
		accesorio unique : true
		orden range:1..100, unique : true
		
    }
}
