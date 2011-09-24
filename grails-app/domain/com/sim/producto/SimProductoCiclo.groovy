package com.sim.producto

import com.sim.catalogo.SimCatPeriodicidad

class SimProductoCiclo implements Comparable {

	Integer	   numeroCiclo
	String     distribucionPago
	Integer	   plazo
	BigDecimal tasa
	BigDecimal montoMaximo
	Integer    porcentajeFlujoCajaFinanciar
	BigDecimal recargoMontoFijo
	
	SimCatPeriodicidad periodicidadTasa
	
	static belongsTo = [ producto : SimProducto ]
	
	SortedSet ordenAccesorios
	static hasMany = [ordenAccesorios: SimProductoCicloAccesorios]
	
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
		"${producto.nombreProducto} CICLO: ${numeroCiclo}"
	}
	
	int compareTo(obj) {
		numeroCiclo.compareTo(obj.numeroCiclo)
	}

}
