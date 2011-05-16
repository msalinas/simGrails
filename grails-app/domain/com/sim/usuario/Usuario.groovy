package com.sim.usuario

import com.rs.gral.RsPersona;

class Usuario extends SecUser {

	static belongsTo =   [ persona : RsPersona ]
	
	static constraints = {
		persona nullable:true
	}
	
	//Here we configure GORM to load the associated persona instance (through the persona property) whenever an Usuario is loaded.
	static mapping = {
		persona lazy:false
	}
	
	String toString() {
		"${username}"
	}
	
}
