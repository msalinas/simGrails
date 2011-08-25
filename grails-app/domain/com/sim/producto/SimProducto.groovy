package com.sim.producto

import java.math.BigDecimal;

import com.sim.catalogo.SimCatMetodoCalculo
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.catalogo.SimCatFormaEntrega

class SimProducto {

	String  			claveProducto
	String  			nombreProducto
	Date    			fechaInicioActivacion
	Date    			fechaFinActivacion
	String  			aplicaA
	SimCatMetodoCalculo metodoCalculo
	SimCatPeriodicidad  periodicidad
	BigDecimal 			montoMinimo
	SimCatFormaEntrega  formaEntrega
	Boolean 			garantias = false
	Boolean 			asignarTodasSucursales = true
	
    static constraints = {
		claveProducto(size:1..2, unique: true, nullable: false, blank: false)
		nombreProducto(size:5..20, unique: true, nullable: false, blank: false)
		fechaInicioActivacion(nullable: false)
		fechaFinActivacion(nullable: false)
		aplicaA(inList:["INDIVIDUAL", "GRUPO"] )
		metodoCalculo()
		periodicidad()
		montoMinimo scale:2, nullable:true
		formaEntrega()
		garantias()
		asignarTodasSucursales()
    }
	
	String toString() {
		"Producto ${nombreProducto}"
	}
	
}
