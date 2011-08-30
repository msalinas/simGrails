package com.sim.regional

import com.sim.empresa.RsEmpleado
import com.rs.gral.RsGralTelefono
import com.rs.gral.RsGralDomicilio
//import java.util.SortedSet; //AL PARECER NO SE REQUIERE

class SimSucursal {
	//MODELO DESARROLLADO A PARTIR DEL EJEMPLO DE SimRegional
 	String  claveSucursal
	String  nombreSucursal
	
	RsEmpleado  gerente
	RsEmpleado  coordinador
	
	SimRegional regional
	
	SortedSet cajas //ORDENA LA LISTA DE LAS CAJAS
	static belongsTo = [ SimRegional, RsEmpleado ] //RELACION MUCHOS A MUCHOS RsEmpleado Y SimSucursal
	static hasMany = [telefono : RsGralTelefono, domicilio : RsGralDomicilio, empleadosAccesoSucursal: RsEmpleado, cajas :SimSucursalCaja]
		
    static constraints = {
		claveSucursal(size:5..15, unique: true, nullable: false, blank: false)
		nombreSucursal(size:5..50, unique: true, nullable: false, blank: false)

		gerente(nullable: true, validator: { empleadoGerente, simSucursal -> 
			empleadoGerente?.puesto?.clavePuesto == 'GERSUC'  || empleadoGerente?.puesto?.clavePuesto == null })
		//GERSUC = GERENTE DE SUCURSAL
		coordinador(nullable: true, validator: { empleadoCoordinador, simSucursal -> 
			empleadoCoordinador?.puesto?.clavePuesto == 'COOSUC'  || empleadoCoordinador?.puesto?.clavePuesto == null })
		//COOREG = COORDINADOR DE SUCURSAL

		domicilio()
		telefono()
		regional()
		//CHECAR COMO QUITAR empleadosAccesoSucursal
    }
	
	String toString() {
		"${nombreSucursal}"
	}
}
