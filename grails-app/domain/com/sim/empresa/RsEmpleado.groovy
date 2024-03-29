package com.sim.empresa

import com.sim.catalogo.SimCatPuesto
import com.sim.catalogo.SimCatPerfil
import com.sim.regional.SimSucursal
import com.sim.regional.SimRegional
import com.rs.gral.RsPersona

class RsEmpleado {
	
	Date    fechaIngreso
	String  numeroNomina
	Boolean esVigente = false
	Boolean asignarTodasSucursales = false
	
	SimCatPuesto puesto
	SimCatPerfil perfil
	RsPersona    persona
	//SE GENERARON VARIOS PROBLEMAS AL ASIGNAR EL ATRIBUTO sucursalPertenece DE TIPO SimSucursal
	//RAZON POR LA QUE SE DEFINIO DE TIPO ENTERO
	Integer      sucursalPertenece
	
	//RELACION MUCHOS A MUCHOS RsEmpleado Y SimRegional
	static hasMany = [sucursalesConAcceso : SimSucursal, regionalesConAcceso : SimRegional]
	//SE MAPEA QUE EL ATRIBUTO sucursalesConAcceso CON EL ATRIBUTO DE empleadosSucursal DEL DOMINIO SimSucursal
	//SE MAPEA QUE EL ATRIBUTO regionalesConAcceso CON EL ATRIBUTO DE empleadosRegion DEL DOMINIO SimRegional
	static mappedBy = [sucursalesConAcceso:"empleadosAccesoSucursal",regionalesConAcceso:"empleadosAccesoRegion"]

    static constraints = {
		persona(unique: true , validator: { personaReferencia, RsReferenciaCliente ->
			personaReferencia?.tiposPersona?.claveTipoPersona?.contains('EMPLEADO') })
		puesto(nullable: false)
		perfil(nullable: false)
		sucursalPertenece()
		fechaIngreso()
		numeroNomina nullable:false, blank: false
		esVigente()
		asignarTodasSucursales()
		sucursalesConAcceso()
		regionalesConAcceso()
    }
	
	String toString() {
		"${puesto.nombrePuesto}: ${persona.apellidoPaterno} ${persona.apellidoMaterno ?: ""} ${persona.primerNombre} ${persona.segundoNombre ?: ""}"
	}
}
