package com.sim.empresa

import com.sim.catalogo.SimCatDocumento

class RsClienteDocumentacion {

	Date fechaRecibido
	Boolean documentacionCorrecta = false
	RsEmpleado asesorVerifico

	static belongsTo = [documento : SimCatDocumento, cliente : RsCliente]
	
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
