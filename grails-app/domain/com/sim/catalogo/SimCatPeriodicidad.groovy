package com.sim.catalogo

class SimCatPeriodicidad {

 	String  clavePeriodicidad
	String  nombrePeriodicidad
	Integer cantidadPagos
	Integer numeroDias
	
	static hasMany =  [ tasaReferencia : SimCatTasaReferencia ] 
	//SI SE ASIGNA SOLO: static hasMany =  SimCatTasaReferencia EXISTE PROBLEMAS AL BORRAR UNA PERIODICIDAD Y ESTA SE ENCUENTRA DEFINIDA
	//EN SimCatTasaReferencia YA QUE NO PERMITE OBTENER LOS REGISTROS DE SimCatTasaReferencia

    static constraints = {
		clavePeriodicidad(size:5..15, unique: true, nullable: false, blank: false)
		nombrePeriodicidad(size:5..50, unique: true, nullable: false, blank: false)
		cantidadPagos(range:1..999,nullable:false)
		numeroDias(range:1..999,nullable:false)
		tasaReferencia(display:false) //NO ESTA TOMANDO EN CUENTA EL DISPLAY
    }

	String toString() {
		"${nombrePeriodicidad}"
	}
}
