package com.sim.cliente

import java.util.Date;
import com.sim.catalogo.SimCatTipoNegocio
import com.sim.catalogo.SimCatGiro
import com.sim.catalogo.SimCatUbicacionNegocio
import com.rs.gral.RsGralTelefono

class SimClienteNegocio {
	
	String  nombreNegocio
	String  rfc
	Date    fechaInicioNegocio
	Integer personasTrabajando
	
	SimCatTipoNegocio      tipoNegocio
	SimCatGiro             giro
	SimCatUbicacionNegocio ubicacionNegocio
	
	static belongsTo = [cliente : RsCliente]
	
	static hasMany = [ telefonos : RsGralTelefono]

    static constraints = {
		nombreNegocio size:5..25, blank: false, unique: false
		tipoNegocio()
		rfc nullable: true
		fechaInicioNegocio nullable:true
		personasTrabajando range:0..50
		giro()
		ubicacionNegocio()
    }
	
	String toString() {
		"${nombreNegocio} - ${tipoNegocio.nombreTipoNegocio} "
	}
}
