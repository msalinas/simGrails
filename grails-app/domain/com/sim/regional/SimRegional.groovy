package com.sim.regional

import com.sim.empresa.RsConfEmpresa
import com.rs.gral.RsGralTelefono
import com.rs.gral.RsGralDomicilio

class SimRegional {
	
	String  claveRegional
	String  nombreRegional
	String  gerente
	String  coordinador
	RsConfEmpresa rsConfEmpresa
	
	static hasMany = [  sucursal : SimSucursal, telefono : RsGralTelefono, domicilio : RsGralDomicilio ]
	
    static constraints = {
		claveRegional(size:5..15, unique: true, nullable: false, blank: false)
		nombreRegional(size:5..50, unique: true, nullable: false, blank: false)
		gerente()
		coordinador()
		domicilio()
		telefono()
		sucursal()
		rsConfEmpresa(nullable: false)
    }
	
	String toString() {
		"${nombreRegional}"
	}
}
