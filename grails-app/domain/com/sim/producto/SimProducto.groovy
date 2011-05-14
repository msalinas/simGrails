package com.sim.producto

class SimProducto {

	String  claveProducto
	String  nombreProducto
	Date    fechaInicioActivacion
	Date    fechaFinActivacion
	
    static constraints = {
		claveProducto(size:1..2, unique: true, nullable: false, blank: false)
		nombreProducto(size:5..20, unique: true, nullable: false, blank: false)
		fechaInicioActivacion(nullable: false)
		fechaFinActivacion(nullable: false)
    }
	
	String toString() {
		"Producto ${nombreProducto}"
	}
	
}
