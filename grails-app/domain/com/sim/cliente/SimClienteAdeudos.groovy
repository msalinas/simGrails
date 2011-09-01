package com.sim.cliente

import com.sim.catalogo.SimCatPeriodicidad;

class SimClienteAdeudos {
	
	String     nombreInstitucionDebe
	BigDecimal montoPrestado
	BigDecimal saldo
	Date       fechaPrestamo
	
	SimCatPeriodicidad   frecuenciaPago
	
	static belongsTo = [cliente : RsCliente]

    static constraints = {
		nombreInstitucionDebe(size:5..50, unique: true, nullable: false, blank: false)
		montoPrestado scale:2, nullable:true
		saldo scale:2, nullable:true
		fechaPrestamo(nullable: false)
		frecuenciaPago()
		cliente()
    }
	
	String toString() {
		"${nombreInstitucionDebe}"
	}
}
