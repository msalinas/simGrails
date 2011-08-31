package com.sim.catalogo

class SimCatUbicacionNegocio {

	String  claveUbicacionNegocio
	String  nombreUbicacionNegocio

    static constraints = {
		claveUbicacionNegocio(size:5..15, unique: true, nullable: false, blank: false)
		nombreUbicacionNegocio(size:5..80, unique: true, nullable: false, blank: false)
    }
}
