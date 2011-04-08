package com.sim.catalogo

import com.sim.empresa.RsConfEmpresa;

class SimCatPuesto {
	String  clavePuesto
	String  nombrePuesto
	String  descripcionPuesto
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		clavePuesto(size:5..15, unique: true, nullable: false, blank: false)
		nombrePuesto(size:5..50, unique: true, nullable: false, blank: false)
		descripcionPuesto(size:5..150)
		dependeDe(nullable: true)
		rsConfEmpresa(nullable: false)
    }
	
	static belongsTo = [dependeDe: SimCatPuesto]
	static hasMany = [ puestosDependientes: SimCatPuesto ]

	String toString() {
		"${nombrePuesto}"
	}
}
