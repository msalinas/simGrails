package com.sim.cliente

import com.sim.catalogo.SimCatDocumento
import com.sim.empresa.RsEmpleado;

class RsClienteDocumentacion {

	Date fechaRecibido
	Boolean documentacionCorrecta = false
	RsEmpleado asesorVerifico
	SimCatDocumento documento

	static belongsTo = [ cliente : RsCliente]
	
    static constraints = {
		cliente()
		documento()
		fechaRecibido()
		asesorVerifico  nullable: true
		documentacionCorrecta()
    }
	
	String toString() {
		"${documento.nombreDocumento}"
	}
}
