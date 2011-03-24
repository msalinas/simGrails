package com.sim.producto

import com.sim.empresa.RsConfEmpresa

class SimProducto {

	String  claveProducto
	String  nombreProducto
	Date    fechaInicioActivacion
	Date    fechaFinActivacion
	RsConfEmpresa rsConfEmpresa
	
    static constraints = {
		claveProducto(size:1..2, unique: true, nullable: false, blank: false)
		nombreProducto(size:5..20, unique: true, nullable: false, blank: false)
		fechaInicioActivacion(nullable: false)
		fechaFinActivacion(nullable: false)
		rsConfEmpresa(nullable: false)
    }
	
	String toString() {
		"Producto ${nombreProducto}"
	}
	
}
