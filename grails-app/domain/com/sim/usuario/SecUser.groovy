package com.sim.usuario

import com.sim.empresa.RsEmpleado

class SecUser {

	String username
	String password
	String email
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	String apellidoPaterno
	String apellidoMaterno
	String primerNombre
	String segundoNombre


	static constraints = {
		username blank: false, unique: true
		password blank: false
		email    email:true, blank:false
		apellidoPaterno size:5..25, blank: false, unique: false
		apellidoMaterno nullable: true, size:0..25
		primerNombre size:5..25, blank: false, unique: false
		segundoNombre nullable: true, size:0..25
	}
	
	static belongsTo =  RsEmpleado

	static mapping = {
		password column: '`password`'
	}

	Set<SecRole> getAuthorities() {
		SecUserSecRole.findAllBySecUser(this).collect { it.secRole } as Set
	}
	
	String toString() {
		"${apellidoPaterno} ${apellidoMaterno ?: ""} ${primerNombre} - ${username}"
	}
}
