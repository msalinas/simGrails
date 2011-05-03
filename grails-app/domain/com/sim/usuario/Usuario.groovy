package com.sim.usuario

import com.rs.gral.RsPersona;


class Usuario extends SecUser {

	static belongsTo =   [ persona : RsPersona ]
	
	static constraints = {
		persona nullable:true
	}
	
	String toString() {
		"${username}"
	}
	
}
