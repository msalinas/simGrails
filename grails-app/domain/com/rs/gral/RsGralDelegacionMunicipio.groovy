package com.rs.gral

class RsGralDelegacionMunicipio {

	String  nombreDelegacionMunicipio
	
	static constraints = {
		nombreDelegacionMunicipio(size:3..50, unique: false,nullable: false, blank: false)
	}
	
	static belongsTo = [ ciudad : RsGralCiudad ]
	
	String toString() {
		"${nombreDelegacionMunicipio}"
	}
}
