package com.sim.usuario

import com.rs.gral.RsPersona

class Usuario extends SecUser {

	/*
	String email
	String apellidoPaterno
	String apellidoMaterno
	String primerNombre
	String segundoNombre
	*/

	static belongsTo =   [ persona : RsPersona ]
	
	static constraints = {
		//email email:true, blank:false
		//apellidoPaterno size:5..25, blank: false, unique: false
		//apellidoMaterno nullable: true, size:0..25
		//primerNombre size:5..25, blank: false, unique: false
		//segundoNombre nullable: true, size:0..25
		persona nullable:true
	}
	
	String toString() {
		"${username}"
	}
	
}
