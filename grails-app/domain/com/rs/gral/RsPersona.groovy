package com.rs.gral

import com.sim.empresa.RsConfEmpresa
import com.sim.usuario.Usuario
import com.sim.empresa.RsEmpleado
import com.sim.catalogo.SimCatDocumento
import com.sim.catalogo.SimCatEscolaridad
import com.sim.catalogo.SimCatTipoPersona

class RsPersona {
 	
	String email
	String apellidoPaterno
	String apellidoMaterno
	String primerNombre
	String segundoNombre
	String nombreAlterno
	String sexo
	String estadoCivil
	Date   fechaNacimiento
	RsConfEmpresa rsConfEmpresa
	String numeroIdentificacionOficial
	String rfc
	String curp
	Usuario usuario
	
	static hasOne  = [ datosEmpleo : RsEmpleado]
	static hasMany = [ telefonos   : RsGralTelefono, domicilios : RsGralDomicilio , rolesPersona : SimCatTipoPersona]
	static belongsTo = [identificacionOficial : SimCatDocumento, escolaridad : SimCatEscolaridad]
	
	static mapping = {
		usuario lazy:false
		datosEmpleo lazy:false
	}

    static constraints = {
		email email:true, blank:false
		apellidoPaterno size:5..25, blank: false, unique: false
		apellidoMaterno nullable: true, size:0..25
		primerNombre size:5..25, blank: false, unique: false
		segundoNombre nullable: true, size:0..25
		sexo(nullable: true, inList:["Masculino", "Femenino"] )
		estadoCivil nullable: true, inList:["Casado - Bienes Mancomunados", "Casado - Bienes Separados", "Divorciado", "Soltero", "Unión Libre", "Viudo"]
		fechaNacimiento(nullable:true)
		usuario nullable:true, unique: true
		telefonos()
		domicilios()
		datosEmpleo unique: true, nullable: true
		nombreAlterno nullable: true, size:0..50
		identificacionOficial nullable: true
		numeroIdentificacionOficial nullable: true
		rfc nullable: true
		curp nullable: true
		escolaridad  nullable: true
		rolesPersona nullable: true
		rsConfEmpresa(nullable: true)
    }
	
	String toString() {
		"${apellidoPaterno} ${apellidoMaterno ?: ""} ${primerNombre} ${segundoNombre ?: ""}"
	}
}
