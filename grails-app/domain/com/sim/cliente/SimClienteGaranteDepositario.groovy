package com.sim.cliente

import com.rs.gral.RsPersona;

class SimClienteGaranteDepositario {

	Integer    dependientesEconomicos
	Boolean    listaNegra = false

	RsPersona  persona
	
	//static belongsTo = [cliente : RsCliente]

	static constraints = {
		persona(unique: true , validator: { personaReferencia, RsReferenciaCliente ->
			personaReferencia?.tiposPersona?.claveTipoPersona?.contains('GARDEP')
		})
		dependientesEconomicos range:0..30
		listaNegra()
	}

	String toString() {
		"${persona.apellidoPaterno} ${persona.apellidoMaterno ?: ""} ${persona.primerNombre} ${persona.segundoNombre ?: ""}"
	}
}
