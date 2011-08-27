package com.sim.regional

import com.sim.empresa.RsEmpleado
import com.rs.gral.RsGralTelefono
import com.rs.gral.RsGralDomicilio

class SimSucursal {

 	String  claveSucursal
	String  nombreSucursal
	String  gerente
	String  coordinador
	
	SimRegional regional
	
	static hasMany = [ telefono : RsGralTelefono, domicilio : RsGralDomicilio], RsEmpleado
	
	static belongsTo = [ SimRegional, RsEmpleado ]
	
    static constraints = {
		claveSucursal(size:5..15, unique: true, nullable: false, blank: false)
		nombreSucursal(size:5..50, unique: true, nullable: false, blank: false)
		gerente()
		coordinador()
		domicilio()
		telefono()
		regional()
    }
	
	String toString() {
		"${nombreSucursal}"
	}
}
