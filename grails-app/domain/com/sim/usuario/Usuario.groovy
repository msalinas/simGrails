package com.sim.usuario

import com.sim.empresa.RsEmpleado

class Usuario extends SecUser {

	String email
	String apellidoPaterno
	String apellidoMaterno
	String primerNombre
	String segundoNombre

	static belongsTo =  RsEmpleado
	
	static constraints = {
		email    email:true, blank:false
		apellidoPaterno size:5..25, blank: false, unique: false
		apellidoMaterno nullable: true, size:0..25
		primerNombre size:5..25, blank: false, unique: false
		segundoNombre nullable: true, size:0..25
	}
	
	String toString() {
		"${apellidoPaterno} ${apellidoMaterno ?: ""} ${primerNombre} - ${username}"
	}
	
}
