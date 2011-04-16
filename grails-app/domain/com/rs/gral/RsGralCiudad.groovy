package com.rs.gral

class RsGralCiudad {

 	String  nombreCiudad
	
	static constraints = {
		nombreCiudad(size:3..50, unique: false,nullable: false, blank: false)
	}
	
	static belongsTo = [ estado : RsGralEstado ]
	static hasMany = [ delegacionMunicipio : RsGralDelegacionMunicipio ]
	
	String toString() {
		"${nombreCiudad}"
	}
}
