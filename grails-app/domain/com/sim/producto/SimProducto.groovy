package com.sim.producto

import com.sim.catalogo.SimCatMetodoCalculo
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.catalogo.SimCatFormaEntrega
import com.sim.catalogo.SimCatTipoPersona
import com.sim.catalogo.SimCatDocumento
import com.sim.regional.SimSucursal

class SimProducto {

	String  	claveProducto
	String  	nombreProducto
	Date    	fechaInicioActivacion
	Date    	fechaFinActivacion
	String  	aplicaA
	BigDecimal 	montoMinimo
	Boolean 	garantias = false
	Boolean 	asignarTodasSucursales = true

	SimCatMetodoCalculo metodoCalculo
	SimCatPeriodicidad  periodicidad
	SimCatFormaEntrega  formaEntrega

	SortedSet ciclos, etapasActividades
	static hasMany = [ participantesCredito : SimCatTipoPersona, sucursales : SimSucursal, ciclos : SimProductoCiclo,
				cargosComisiones : SimProductoCargoComision, etapasActividades : SimProductoEtapaActividad, documentos : SimCatDocumento ]
	
    static constraints = {
		claveProducto(size:1..20, unique: true, nullable: false, blank: false)
		nombreProducto(size:5..50, unique: true, nullable: false, blank: false)
		fechaInicioActivacion(nullable: false)
		fechaFinActivacion(nullable: false)
		aplicaA(inList:["INDIVIDUAL", "GRUPO"] )
		metodoCalculo()
		periodicidad()
		montoMinimo scale:2, nullable:true
		formaEntrega()
		garantias()
		asignarTodasSucursales()
		ciclos()
		participantesCredito()
		sucursales()
		cargosComisiones()
		etapasActividades()
		documentos()
    }
	
	String toString() {
		"PRODUCTO ${nombreProducto}"
	}
	
}
