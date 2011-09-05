package com.sim.producto

import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatPeriodicidad;

class SimProductoCargoComision {
	
	String     formaAplicacion
	BigDecimal cargoInicial
	Integer    porcentajeMontoPrestado
	BigDecimal cantidadFija
	BigDecimal valor
	String     unidad
	
	SimCatAccesorio    cargoComision
	SimCatPeriodicidad periodicidadValorUnidad
	
	static belongsTo = [ producto : SimProducto ]

    static constraints = {
		producto()
		cargoComision(nullable: false, validator: { accesorioCargoComision, productoCargoComision ->
			accesorioCargoComision?.tipoAccesorio?.claveTipoAccesorio == 'CARGO_COMISION'})
		formaAplicacion(inList:["CARGO INICIAL", "PERIODICAMENTE DEPENDIENDO DEL MONTO PRESTADO", 
			"PERIODICAMENTE DEPENDIENDO DEL SALDO A LA FECHA","FIJO"] )
		cargoInicial scale:2, nullable:true
		porcentajeMontoPrestado range:1..100, nullable:true
		cantidadFija scale:2, nullable:true
		valor scale:2, nullable:true
		unidad(nullable: true,inList:["AL MILLAR","PORCENTUAL"])
		periodicidadValorUnidad(nullable: true)
    }
	
	String toString() {
		"${cargoComision.nombreAccesorio}: ${formaAplicacion}"
	}
	
}
