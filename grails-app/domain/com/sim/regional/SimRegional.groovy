package com.sim.regional

import com.sim.empresa.RsEmpleado
import com.rs.gral.RsGralTelefono
import com.rs.gral.RsGralDomicilio

class SimRegional {
	
	String  claveRegional
	String  nombreRegional
	String  gerente
	//RsEmpleado gerente  //Al implementar el gerente no funcionan el campo regionalesConAcceso en el dominio RsEmpleado
	String  coordinador
	
	static hasMany = [  sucursal : SimSucursal, telefono : RsGralTelefono, domicilio : RsGralDomicilio ], RsEmpleado
	
    static constraints = {
		claveRegional(size:5..15, unique: true, nullable: false, blank: false)
		nombreRegional(size:5..50, unique: true, nullable: false, blank: false)
		gerente()
		//gerente nullable: true, unique: true
		coordinador()
		domicilio()
		telefono()
		sucursal()
    }
	
	String toString() {
		"${nombreRegional}"
	}
}
