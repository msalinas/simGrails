package com.sim.producto

import java.math.BigDecimal;
import com.sim.catalogo.SimCatPeriodicidad

class SimProductoCiclo {

	Integer	   numeroCiclo
	String     distribucionPago
	Integer	   plazo
	BigDecimal tasa
	BigDecimal montoMaximo
	Integer    porcentajeFlujoCajaFinanciar
	BigDecimal recargoMontoFijo
	
	SimCatPeriodicidad periodicidadTasa
	
	static belongsTo = [ producto : SimProducto ] 
	
    static constraints = {
		producto()
		numeroCiclo range:1..15
		distribucionPago (nullable: false, inList:["CAPITAL-ACCESORIOS", "ACCESORIOS-CAPITAL"] )
		plazo range:1..40
		tasa scale:2, nullable:false
		periodicidadTasa()
		montoMaximo scale:2, nullable:false
		porcentajeFlujoCajaFinanciar range:1..100
		recargoMontoFijo scale:2, nullable:true
    }
	
	String toString() {
		"CICLO: ${numeroCiclo}"
	}

}
