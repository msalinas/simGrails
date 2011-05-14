package com.sim.regional

import com.sim.empresa.RsEmpleado
import com.rs.gral.RsGralTelefono
import com.rs.gral.RsGralDomicilio

class SimRegional {
	
	String  claveRegional
	String  nombreRegional
	String  gerente
	String  coordinador
	
	static hasMany = [  sucursal : SimSucursal, telefono : RsGralTelefono, domicilio : RsGralDomicilio ], RsEmpleado
	
    static constraints = {
		claveRegional(size:5..15, unique: true, nullable: false, blank: false)
		nombreRegional(size:5..50, unique: true, nullable: false, blank: false)
		gerente()
		coordinador()
		domicilio()
		telefono()
		sucursal()
    }
	
	String toString() {
		"${nombreRegional}"
	}
}
