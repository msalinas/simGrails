package com.rs.gral

import com.sim.empresa.RsConfEmpresa
import com.sim.usuario.Usuario
import com.sim.empresa.RsEmpleado

class RsPersona {
 	
	String email
	String apellidoPaterno
	String apellidoMaterno
	String primerNombre
	String segundoNombre
	RsConfEmpresa rsConfEmpresa
	Usuario usuario
	RsEmpleado empleado	
	
	static mapping = {
		usuario lazy:false
		empleado lazy:false
	}
	
	static hasMany = [ telefonos : RsGralTelefono, domicilios : RsGralDomicilio  ]

    static constraints = {
		email email:true, blank:false
		apellidoPaterno size:5..25, blank: false, unique: false
		apellidoMaterno nullable: true, size:0..25
		primerNombre size:5..25, blank: false, unique: false
		segundoNombre nullable: true, size:0..25
		usuario nullable:true, unique: true
		telefonos()
		domicilios()
		empleado nullable: true
		rsConfEmpresa(nullable: true)
    }
	
	String toString() {
		"${apellidoPaterno} ${apellidoMaterno ?: ""} ${primerNombre}"
	}
}
