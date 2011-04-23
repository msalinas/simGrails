package com.rs.gral

import com.sim.empresa.RsConfEmpresa;
import com.sim.regional.SimRegional
import com.sim.regional.SimSucursal

class RsGralDomicilio {

	String  calle
	String  numeroInterior
	String  numeroExterior
	RsConfEmpresa rsConfEmpresa
	RsGralAsentamiento codigoPostal
	Boolean esFiscal = false
	String  comentarios
	
	static belongsTo = [ regional : SimRegional, sucursal : SimSucursal ]

    static constraints = {
		calle(size:5..100, nullable: false, blank: false)
		numeroInterior()
		numeroExterior()
		codigoPostal(nullable: false)
		esFiscal()
		comentarios(size:0..300)
		rsConfEmpresa(nullable: false)
		regional(nullable: true)
		sucursal(nullable: true)
    }
	
	String toString() {
		"${calle} ${numeroInterior} ${numeroExterior} ${codigoPostal}"
	}
}
