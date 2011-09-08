package com.sim.catalogo

class SimCatEtapaActividad {

	String  claveActividad
	String  nombreActividad
	String  descripcionActividad
	
	static belongsTo = [etapa : SimCatEtapaPrestamo]
	
    static constraints = {
		etapa()
		claveActividad(size:3..20, unique: true, nullable: false, blank: false)
		nombreActividad(size:5..50, unique: true, nullable: false, blank: false)
		descripcionActividad(size:5..250, unique: true, nullable: false, blank: false)
    }
	String toString() {
		"${etapa.nombreEtapaPrestamo} - ${nombreActividad}"
	}
}
