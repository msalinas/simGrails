package com.sim.empresa

import com.rs.gral.RsPersona;

class RsCliente {

	RsPersona persona
	BigDecimal ingresoSemanal
	Integer dependientesEconomicos
	String destinoDelCredito
	String rolEnElHogar
	Boolean listaNegra = false
	
    static constraints = {
		persona unique: true
		ingresoSemanal scale:2, nullable:true
		dependientesEconomicos range:0..30
		destinoDelCredito inList:[
			"ADQUIRIR O COMPRAR MERCANCIA", 
			"COMPRAR MAQUINARIA, EQUIPO O HERRAMIENTAS", 
			"AMPLIAR, ADECUAR O REPARAR EL LOCAL O VEHICULO", 
			"COMPRAR LOCAL O VEHICULO", 
			"PAGAR DEUDAS DEL NEGOCIO",
			"OTRO FIN RELACIONADO", 
			"FINES AJENOS AL NEGOCIO"]
		rolEnElHogar inList:[
			"JEFE(A)",
			"ESPOSO(A)",
			"HIJO(A)",
			"OTRO"]
		listaNegra()
    }
}
