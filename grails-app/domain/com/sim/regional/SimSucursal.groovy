package com.sim.regional

import com.sim.empresa.RsConfEmpresa;
import com.rs.gral.RsGralTelefono
import com.rs.gral.RsGralDomicilio

class SimSucursal {

 	String  claveSucursal
	String  nombreSucursal
	String  gerente
	String  coordinador
	RsConfEmpresa rsConfEmpresa
	
	static hasMany = [ telefono : RsGralTelefono, domicilio : RsGralDomicilio ]
	
	static belongsTo = [ regional : SimRegional]
	
    static constraints = {
		claveSucursal(size:5..15, unique: true, nullable: false, blank: false)
		nombreSucursal(size:5..50, unique: true, nullable: false, blank: false)
		gerente()
		coordinador()
		domicilio()
		telefono()
		regional()
		rsConfEmpresa(nullable: false)
    }
	
	String toString() {
		"${nombreSucursal}"
	}
}
