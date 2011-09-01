package com.sim.cliente

import java.math.BigDecimal;
import java.util.Date;

import com.sim.catalogo.SimCatTipoGarantia

class SimClienteGarantia {
	
	String     descripcionGarantia
	String     numeroFacturaEscritura
	Date       fechaFacturaEscritura
	BigDecimal valorComercialPesos
	BigDecimal valorGarantizaPesos
	Integer    porcentajeCubreGarantia
	
	SimCatTipoGarantia  tipoGarantia
	
	SimClienteGaranteDepositario garanteDepositario
	
	static belongsTo = [ cliente : RsCliente]

    static constraints = {
		tipoGarantia()
		descripcionGarantia(size:5..50, unique: true, nullable: false, blank: false)
		numeroFacturaEscritura(size:5..20, unique: true, nullable: false, blank: false)
		fechaFacturaEscritura()
		valorComercialPesos scale:2, nullable:true
		valorGarantizaPesos scale:2, nullable:true
		porcentajeCubreGarantia range:20..100
		garanteDepositario()
		cliente()
    }
	
	String toString() {
		"${descripcionGarantia}"
	}

}
