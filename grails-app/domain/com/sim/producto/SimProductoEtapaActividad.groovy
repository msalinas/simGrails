package com.sim.producto

import com.sim.catalogo.SimCatEtapaActividad

class SimProductoEtapaActividad implements Comparable {

	Integer orden
	
	SimCatEtapaActividad etapaActividad
	
	static belongsTo = [producto : SimProducto]
	
    static constraints = {
		producto()
		etapaActividad()
		orden range:1..35
    }
	
	String toString() {
		"${orden}. ${etapaActividad.etapa.nombreEtapaPrestamo} - ${etapaActividad.nombreActividad}"
	}
	int compareTo(obj) {
		orden.compareTo(obj.orden)
	}
}
