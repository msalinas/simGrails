package com.sim.empresa

import com.rs.gral.RsPersona

class RsReferenciaCliente {
	
	RsPersona    persona
	String		 tipoReferencia
	
	static belongsTo = [cliente : RsCliente]

    static constraints = {
		persona(unique: true , validator: { personaReferencia, RsReferenciaCliente -> 
			personaReferencia?.tiposPersona?.claveTipoPersona?.contains('REFCLIENTE') })
		tipoReferencia(inList:["TELEFONICA", "VECINAL"] )
		cliente()
    }
	
	String toString() {
		"${persona.apellidoPaterno} ${persona.apellidoMaterno ?: ""} ${persona.primerNombre} ${persona.segundoNombre ?: ""}"
	}
	
}
