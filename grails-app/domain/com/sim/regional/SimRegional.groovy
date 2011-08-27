package com.sim.regional

import com.sim.empresa.RsEmpleado
import com.rs.gral.RsGralTelefono
import com.rs.gral.RsGralDomicilio

class SimRegional {
	
	String  claveRegional
	String  nombreRegional

	RsEmpleado gerente  //SE ASIGNA UN GERENTE A LA REGION, VALIDAR QUE SOLO OBTENGA EMPLEADOS CON EL PUESTO DE GERENTE REGIONAL
	String  coordinador
	
	static belongsTo = RsEmpleado //RELACION MUCHOS A MUCHOS RsEmpleado Y SimRegional
	static hasMany = [  sucursal : SimSucursal, telefono : RsGralTelefono, domicilio : RsGralDomicilio, empleados: RsEmpleado ]
	//SI SOLO SE ESPECIFICA hasMany = RsEmpleado SE GENERA UN PROBLEMA CON EL ATRIBUTO regionalesConAcceso EN EL DOMINIO RsEmpleado
	
    static constraints = {
		claveRegional(size:5..15, unique: true, nullable: false, blank: false)
		nombreRegional(size:5..50, unique: true, nullable: false, blank: false)
		gerente(nullable: true, validator: { empleadoGerente, simRegional -> 
			empleadoGerente?.puesto?.clavePuesto == 'GERREG'  || empleadoGerente?.puesto?.clavePuesto == null })
		coordinador()
		domicilio()
		telefono()
		sucursal()
		//CHECAR COMO QUITAR EMPLEADOS
    }
	
	String toString() {
		"${nombreRegional}"
	}
}
