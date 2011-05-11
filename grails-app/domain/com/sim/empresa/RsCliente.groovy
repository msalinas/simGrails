package com.sim.empresa

import com.rs.gral.RsPersona;

class RsCliente {

	RsPersona persona
	BigDecimal ingresoSemanal
	
    static constraints = {
		persona unique: true
		ingresoSemanal scale:2
    }
}
