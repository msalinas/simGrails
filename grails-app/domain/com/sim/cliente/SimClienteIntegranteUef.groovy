package com.sim.cliente

import com.rs.gral.RsPersona;
import com.sim.catalogo.SimCatParentesco

class SimClienteIntegranteUef {

	RsPersona        persona
	SimCatParentesco parentesco

	static belongsTo = [cliente : RsCliente]

	static constraints = {
		persona(unique: true , validator: { personaReferencia, RsReferenciaCliente ->
			personaReferencia?.tiposPersona?.claveTipoPersona?.contains('UEF')
		})
		parentesco()
		cliente()
	}

	String toString() {
		"${persona.apellidoPaterno} ${persona.apellidoMaterno ?: ""} ${persona.primerNombre} ${persona.segundoNombre ?: ""}"
	}
}
