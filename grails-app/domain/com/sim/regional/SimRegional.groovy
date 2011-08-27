package com.sim.regional

import com.sim.empresa.RsEmpleado
import com.rs.gral.RsGralTelefono
import com.rs.gral.RsGralDomicilio

class SimRegional {
	
	String  claveRegional
	String  nombreRegional
	String  gerente
	RsEmpleado gerentePrueba  //SE ASIGNA UN GERENTE A LA REGION
	//Al implementar el gerente no funcionan el campo regionalesConAcceso en el dominio RsEmpleado
	String  coordinador
	
	static belongsTo = RsEmpleado //RELACION MUCHOS A MUCHOS RsEmpleado Y SimRegional
	static hasMany = [  sucursal : SimSucursal, telefono : RsGralTelefono, domicilio : RsGralDomicilio, empleados: RsEmpleado ]//, RsEmpleado
	
    static constraints = {
		claveRegional(size:5..15, unique: true, nullable: false, blank: false)
		nombreRegional(size:5..50, unique: true, nullable: false, blank: false)
		gerente()
		gerentePrueba nullable: true//, unique: true
		coordinador()
		domicilio()
		telefono()
		sucursal()
		empleados()
    }
	
	String toString() {
		"${nombreRegional}"
	}
}
