package com.rs.gral

import com.sim.empresa.RsConfEmpresa
import com.sim.usuario.Usuario

class RsPersona {
 	
	String email
	String apellidoPaterno
	String apellidoMaterno
	String primerNombre
	String segundoNombre
	RsConfEmpresa rsConfEmpresa
	Usuario usuario
	
	static mapping = {
		usuario lazy:false
	}
	
	static hasMany = [ telefonos : RsGralTelefono, domicilios : RsGralDomicilio  ]

    static constraints = {
		email email:true, blank:false
		apellidoPaterno size:5..25, blank: false, unique: false
		apellidoMaterno nullable: true, size:0..25
		primerNombre size:5..25, blank: false, unique: false
		segundoNombre nullable: true, size:0..25
		usuario(nullable:true)
		telefonos()
		domicilios()
		rsConfEmpresa(nullable: true)
    }
	
	String toString() {
		"${apellidoPaterno} ${apellidoMaterno ?: ""} ${primerNombre}"
	}
}
