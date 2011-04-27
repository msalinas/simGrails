package com.sim.usuario

class User extends SecUser {
	
	String apellidoPaterno
	String apellidoMaterno
	String primerNombre
	String segundoNombre

    static constraints = {
		apellidoPaterno size:5..25, blank: false, unique: false
		apellidoMaterno nullable: true, size:0..25
		primerNombre size:5..25, blank: false, unique: false
		segundoNombre nullable: true, size:0..25
    }
}
